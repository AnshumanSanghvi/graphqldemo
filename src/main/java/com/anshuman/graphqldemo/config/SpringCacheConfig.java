package com.anshuman.graphqldemo.config;

import jakarta.annotation.Nonnull;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Pair;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Configuration
@EnableCaching
public class SpringCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager() {
            @Nonnull
            @Override
            protected Cache createConcurrentMapCache(@Nonnull String name) {
                return new ConcurrentMapCollectionCache(super.createConcurrentMapCache(name));
            }
        };
    }

    protected static class ConcurrentMapCollectionCache extends CollectionCache {

        protected ConcurrentMapCollectionCache(final Cache cache) {
            super(cache);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected <K> boolean areAllKeysPresentInCache(Iterable<K> keys) {
            ConcurrentMap<Object, Object> nativeCache = (ConcurrentMap<Object, Object>) getNativeCache();
            return getStream(keys)
                    .allMatch(nativeCache::containsKey);
        }
    }

    protected abstract static class CollectionCache implements Cache {

        private final Cache cache;

        protected CollectionCache(Cache cache) {
            Assert.notNull(cache, "Cache must not be null");
            this.cache = cache;
        }

        protected static <T> Stream<T> getStream(Iterable<T> iterable) {
            return StreamSupport
                    .stream(iterable.spliterator(), false);
        }

        private static void checkInstanceOf(Object key, Object value) {
            Assert.isInstanceOf(Iterable.class, value,
                    String.format("Value [%1$s] must be an instance of Iterable when caching multiple keys [%2$s]",
                            ObjectUtils.nullSafeClassName(value), key));
        }

        protected Cache getCache() {
            return this.cache;
        }

        @Nonnull
        @Override
        public String getName() {
            return getCache().getName();
        }

        @Nonnull
        @Override
        public Object getNativeCache() {
            return getCache().getNativeCache();
        }

        protected abstract <K> boolean areAllKeysPresentInCache(Iterable<K> keys);

        protected int sizeOf(Iterable<?> iterable) {
            return (int) getStream(iterable)
                    .count();
        }

        protected <T> List<T> toList(Iterable<T> iterable) {
            return getStream(iterable)
                    .toList();
        }

        @Override
        public ValueWrapper get(@Nonnull Object key) {

            if (key instanceof Iterable<?> keys) {
                if (!areAllKeysPresentInCache(keys)) {
                    return null;
                }
                Collection<Object> values = new ArrayList<>();
                for (Object singleKey : keys) {
                    Object object = Objects.requireNonNull(getCache().get(singleKey)).get();
                    values.add(object);
                }
                return () -> values;
            }
            return getCache().get(key);
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T get(@Nonnull Object key, Class<T> type) {
            if (key instanceof Iterable) {
                Assert.isAssignable(Iterable.class, type,
                        String.format("Expected return type [%1$s] must be Iterable when querying multiple keys [%2$s]",
                                type.getName(), key));
                return (T) Optional
                        .ofNullable(get(key))
                        .map(Cache.ValueWrapper::get)
                        .orElse(null);
            }
            return getCache().get(key, type);
        }

        @Override
        @SuppressWarnings("all")
        public <T> T get(@Nonnull Object key, @Nonnull Callable<T> valueLoader) {
            return (T) get(key, Object.class);
        }

        @Override
        public CompletableFuture<?> retrieve(@Nonnull Object key) {
            return CompletableFuture.completedFuture(get(key));
        }

        @Override
        public void put(@Nonnull Object key, Object value) {
            if (key instanceof Iterable) {
                checkInstanceOf(key, value);
                pairsFromKeysAndValues(toList((Iterable<?>) key), toList((Iterable<?>) value))
                        .forEach(pair -> getCache().put(pair.getFirst(), pair.getSecond()));
            } else {
                getCache().put(key, value);
            }
        }

        @Override
        public ValueWrapper putIfAbsent(@Nonnull Object key, Object value) {
            if (key instanceof Iterable) {
                checkInstanceOf(key, value);
                return () -> pairsFromKeysAndValues(toList((Iterable<?>) key), toList((Iterable<?>) value))
                        .stream()
                        .map(pair -> getCache().putIfAbsent(pair.getFirst(), pair.getSecond()))
                        .toList();
            }
            return getCache().putIfAbsent(key, value);
        }

        @Override
        public void evict(@Nonnull Object key) {
            if (key instanceof Iterable<?> iterableKey) {
                getStream(iterableKey)
                        .forEach(getCache()::evict);
            } else {
                getCache().evict(key);
            }
        }

        @Override
        public void clear() {
            getCache().clear();
        }

        private <K, V> List<Pair<K, V>> pairsFromKeysAndValues(List<K> keys, List<V> values) {
            final int keysSize = keys.size();
            Assert.isTrue(keysSize == values.size(),
                    String.format("The number of values [%1$d] must match the number of keys [%2$d]",
                            values.size(), keysSize));
            return IntStream
                    .range(0, keysSize)
                    .mapToObj(index -> Pair.of(keys.get(index), values.get(index)))
                    .toList();
        }
    }
}




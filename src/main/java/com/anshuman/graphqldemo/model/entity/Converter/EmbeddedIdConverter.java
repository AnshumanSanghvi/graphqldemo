package com.anshuman.graphqldemo.model.entity.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EmbeddedId;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * See: <a href="https://stackoverflow.com/a/73881002">Serialize @EmbeddedId object</a>
 * so that it can work as a link in spring data rest
 */
@Component
@RequiredArgsConstructor
public class EmbeddedIdConverter implements BackendIdConverter {

    private final ObjectMapper objectMapper;

    @Override
    public Serializable fromRequestId(final String id, final Class<?> entityType) {
        return getFieldWithEmbeddedAnnotation(entityType)
                .map(Field::getType)
                .map(ret -> {
                    try {
                        String decodedId = new String(Base64.getUrlDecoder().decode(id));
                        return (Serializable) objectMapper.readValue(decodedId, (Class) ret);
                    } catch (JsonProcessingException ignored) {
                        return null;
                    }
                })
                .orElse(id);
    }

    @Override
    public String toRequestId(final Serializable id, final Class<?> entityType) {
        try {
            String json = objectMapper.writeValueAsString(id);
            return Base64.getUrlEncoder().encodeToString(json.getBytes(UTF_8));
        } catch (JsonProcessingException ignored) {
            return id.toString();
        }
    }

    @Override
    public boolean supports(final Class<?> aClass) {
        return isEmbeddedIdAnnotationPresent(aClass);
    }

    private static boolean isEmbeddedIdAnnotationPresent(Class<?> entity) {
        return getFieldWithEmbeddedAnnotation(entity)
                .isPresent();
    }

    @NotNull
    private static Optional<Field> getFieldWithEmbeddedAnnotation(Class<?> entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(method -> method.isAnnotationPresent(EmbeddedId.class))
                .findFirst();
    }
}

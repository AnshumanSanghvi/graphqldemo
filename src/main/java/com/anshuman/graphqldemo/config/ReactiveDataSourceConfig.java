package com.anshuman.graphqldemo.config;

import com.anshuman.graphqldemo.annotation.Reactive;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.data.r2dbc.dialect.R2dbcDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.core.DatabaseClient;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@Slf4j
@EnableR2dbcRepositories(
        basePackages = {"com.anshuman.graphqldemo.model.reactive"},
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Reactive.class)
        )
public class ReactiveDataSourceConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, "postgresql")
                        .option(HOST, "localhost")
                        .option(PORT, 5432)
                        .option(USER, "anshuman")
                        .option(PASSWORD, "anshuman")
                        .option(DATABASE, "dvdrental")
                        .build());
    }

    @Bean
    public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient
                .builder()
                .connectionFactory(connectionFactory)
                .build();
    }

    @Bean
    public R2dbcDialect dialect() {
        return new PostgresDialect();
    }

    @Bean
    public R2dbcEntityOperations entityOperations(DatabaseClient databaseClient, R2dbcDialect r2dbcDialect) {
        return new R2dbcEntityTemplate(databaseClient, r2dbcDialect);
    }

    @Bean
    public R2dbcRepositoryFactory repositoryFactory(R2dbcEntityOperations r2dbcEntityOperations) {
        return new R2dbcRepositoryFactory(r2dbcEntityOperations);
    }

    @Bean(name = "ReactiveTransactionManager")
    public R2dbcTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

}

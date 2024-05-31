package com.anshuman.graphqldemo.config;

import com.anshuman.graphqldemo.annotation.Reactive;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Configure data source bean and Jdbc Template
 */
@Configuration
@EntityScan(basePackages = {"com.anshuman.graphqldemo.model.entity"})
@EnableJpaRepositories(
        basePackages = {"com.anshuman.graphqldemo.model.repository"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Reactive.class),
        entityManagerFactoryRef = "JpaEntityManagerFactory",
        transactionManagerRef = "JpaTransactionManager")
@EnableTransactionManagement
public class JpaDataSourceConfig {

    @Primary
    @Bean(name = "JpaDataSource")
    public DataSource jpaDataSource() {
        return dataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Value("${spring.datasource.postgres.hibernate.dialect:org.hibernate.dialect.PostgreSQLDialect}")
    private String hibernateDialect;

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("JpaDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "JpaAsyncTaskExecutor")
    public AsyncTaskExecutor taskExecutor() {
        Executor executor = Executors.newCachedThreadPool();
        return new ConcurrentTaskExecutor(executor);
    }

    @Bean(name = "JpaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("JpaDataSource") DataSource dataSource,
            @Qualifier("JpaAsyncTaskExecutor") AsyncTaskExecutor taskExecutor,
            EntityManagerFactoryBuilder builder) {
        var factoryBean = builder
                .dataSource(dataSource)
                .persistenceUnit("JpaPersistenceUnit")
                .packages("com.anshuman.graphqldemo.model.entity")
                .build();
        factoryBean.setJpaDialect(new HibernateJpaDialect());
        factoryBean.setBootstrapExecutor(taskExecutor);
        factoryBean.setJpaVendorAdapter(vendorAdapter());
        return factoryBean;
    }

    @Bean(name = "JpaTransactionManager")
    public PlatformTransactionManager jpaTransactionManager(
            @Qualifier("JpaEntityManagerFactory") LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory
    ) {
        return new JpaTransactionManager(Objects.requireNonNull(jpaEntityManagerFactory.getObject()));
    }

    private JpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setShowSql(true);
        vendorAdapter.setPrepareConnection(true);
        vendorAdapter.setGenerateDdl(false);
        return vendorAdapter;
    }
}

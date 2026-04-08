package com.example.employeeetlspringboot.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.employeeetlspringboot.repository.source",
        entityManagerFactoryRef = "sourceEntityManagerFactory",
        transactionManagerRef = "sourceTransactionManager"
)
public class SourceDbConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.source")
    public DataSource sourceDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean sourceEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("sourceDataSource") DataSource dataSource,
            JpaProperties jpaProperties) {

        HashMap<String, Object> props = new HashMap<>(jpaProperties.getProperties());
        props.put("hibernate.hbm2ddl.auto", "update");

        return builder
                .dataSource(dataSource)
                .packages("com.example.employeeetlspringboot.entity.source")
                .persistenceUnit("source")
                .properties(props)
                .build();
    }

    @Bean
    public PlatformTransactionManager sourceTransactionManager(
            @Qualifier("sourceEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
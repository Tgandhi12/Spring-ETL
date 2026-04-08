package com.example.employeeetlspringboot.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.employeeetlspringboot.repository.target",
        entityManagerFactoryRef = "targetEntityManagerFactory",
        transactionManagerRef = "targetTransactionManager"
)
public class TargetDbConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.target")
    public DataSource targetDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean targetEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("targetDataSource") DataSource dataSource,
            JpaProperties jpaProperties) {

        HashMap<String, Object> props = new HashMap<>(jpaProperties.getProperties());
        props.put("hibernate.hbm2ddl.auto", "update");

        return builder
                .dataSource(dataSource)
                .packages("com.example.employeeetlspringboot.entity.target")
                .persistenceUnit("target")
                .properties(props)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager targetTransactionManager(
            @Qualifier("targetEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
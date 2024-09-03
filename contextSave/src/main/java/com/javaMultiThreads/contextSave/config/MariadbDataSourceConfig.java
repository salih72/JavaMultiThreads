package com.javaMultiThreads.contextSave.config;

import com.javaMultiThreads.contextSave.entities.MariadbData;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.javaMultiThreads.contextSave.repositories.mariadb",
        entityManagerFactoryRef = "mariadbEntityManagerFactory",
        transactionManagerRef = "mariadbTransactionManager"
)
public class MariadbDataSourceConfig {

    @Autowired
    private EntityManagerFactoryBuilder entityManagerFactoryBuilder;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.mariadb")
    public DataSource mariadbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mariadbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mariadbEntityManagerFactory() {
        return entityManagerFactoryBuilder
                .dataSource(mariadbDataSource())
                .packages(MariadbData.class)
                .persistenceUnit("mariadbPU")
                .build();
    }

    @Bean(name = "mariadbTransactionManager")
    public PlatformTransactionManager mariadbTransactionManager(
            @Qualifier("mariadbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

package com.multiThreads.threadExample.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.multiThreads.threadExample.repository.postgresql",
        entityManagerFactoryRef = "postgresqlEntityManagerFactory",
        transactionManagerRef = "postgresqlTransactionManager"
)
public class PostgreSqlDataSourceConfig {

    @Bean(name = "postgresqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    public DataSource postgresqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(
            @Qualifier("postgresqlDataSource") DataSource postgresqlDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgresqlDataSource);
        em.setPackagesToScan("com.multiThreads.threadExample.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        em.setJpaPropertyMap(properties);

        return em;
    }


    @Bean(name = "postgresqlTransactionManager")
    public PlatformTransactionManager postgresqlTransactionManager(@Qualifier("postgresqlEntityManagerFactory") LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory) {
        return new JpaTransactionManager(postgresqlEntityManagerFactory.getObject());
    }
}
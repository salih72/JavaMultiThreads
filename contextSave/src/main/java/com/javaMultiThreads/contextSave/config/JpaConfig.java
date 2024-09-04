package com.javaMultiThreads.contextSave.config;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JpaConfig {

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
            JpaVendorAdapter jpaVendorAdapter,
            ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {

        // Boş bir HashMap kullanarak Map parametresini null olmayacak şekilde düzenliyoruz.
        Map<String, Object> jpaProperties = new HashMap<>();

        return new EntityManagerFactoryBuilder(jpaVendorAdapter, jpaProperties, persistenceUnitManager.getIfAvailable());
    }
}

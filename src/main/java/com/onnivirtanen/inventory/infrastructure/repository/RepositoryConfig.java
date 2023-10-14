package com.onnivirtanen.inventory.infrastructure.repository;

import com.onnivirtanen.inventory.domain.port.out.InventoryRepository;
import com.onnivirtanen.inventory.infrastructure.repository.inmemory.InMemoryInventoryRepository;
import com.onnivirtanen.inventory.infrastructure.repository.jpa.JpaInventoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public InventoryRepository inventoryRepository() {
        /**
         * If you want an inMemoryRepository return it instead
         */
        //return new JpaInventoryRepository();
        return new InMemoryInventoryRepository();
    }
}

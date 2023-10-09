package com.onnivirtanen.inventory.infrastructure;

import com.onnivirtanen.inventory.domain.port.out.InventoryRepository;
import com.onnivirtanen.inventory.infrastructure.jpa.JpaInventoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public InventoryRepository inventoryRepository() {
        /**
         * If you want an inMemoryRepository return it instead
         */
        return new JpaInventoryRepository();
    }
}

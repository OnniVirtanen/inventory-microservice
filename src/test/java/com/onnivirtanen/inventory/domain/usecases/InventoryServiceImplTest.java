package com.onnivirtanen.inventory.domain.usecases;

import com.onnivirtanen.inventory.domain.command.AddNewProductCommand;
import com.onnivirtanen.inventory.domain.command.ReStockProductCommand;
import com.onnivirtanen.inventory.domain.event.DomainEvent;
import com.onnivirtanen.inventory.domain.exception.ProductAlreadyExistsException;
import com.onnivirtanen.inventory.domain.exception.ProductNotFoundException;
import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.model.valueobject.Quantity;
import com.onnivirtanen.inventory.domain.port.out.DomainEventPublisher;
import com.onnivirtanen.inventory.domain.port.out.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

    @Mock
    private InventoryRepository repository;

    @Mock
    private DomainEventPublisher publisher;

    @InjectMocks
    private InventoryServiceImpl service;

    @Test
    void addNewProduct_regularScenario_success() {
        // Arrange
        AddNewProductCommand command = TestData.addNewProductCommand();
        when(repository.productExistsByEAN(command.barcode())).thenReturn(false);
        Product product = Product.from(command);
        when(repository.save(product)).thenReturn(product);

        // Act
        Product savedProduct = service.addNewProduct(command);

        // Assert
        assertEquals(product, savedProduct);
        verify(repository).productExistsByEAN(command.barcode());
        verify(repository).save(product);
        verify(publisher).publish(any(DomainEvent.class));
    }

    @Test
    void addNewProduct_sameEANBarcode_throws() {
        // Arrange
        AddNewProductCommand command = TestData.addNewProductCommand();
        when(repository.productExistsByEAN(command.barcode())).thenReturn(true);

        // Act & Assert
        assertThrows(ProductAlreadyExistsException.class, () -> service.addNewProduct(command));
    }

    @Test
    void reStockProduct_regularScenario_success() {
        // Arrange
        Product product = TestData.product();
        Quantity initialAmount = product.getQuantity();
        Quantity reStockAmount = new Quantity((long) 1);
        ReStockProductCommand command = new ReStockProductCommand(product.getId(), reStockAmount);
        when(repository.findById(command.productId())).thenReturn(Optional.of(product));

        // Act
        when(repository.update(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Product restockedProduct = service.reStockProduct(command);

        // Assert
        assertEquals(new Quantity(initialAmount.getAmount() + reStockAmount.getAmount()),
                restockedProduct.getQuantity());
        verify(repository).findById(command.productId());
        verify(repository).update(product);
        verify(publisher).publish(any(DomainEvent.class));
    }

    @Test
    void reStockProduct_noProductById_throws() {
        // Arrange
        ReStockProductCommand command = TestData.reStockProductCommand();
        when(repository.findById(command.productId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> service.reStockProduct(command));
    }
 }
package com.onnivirtanen.inventory.domain.aggregate;

import com.onnivirtanen.inventory.domain.entity.Category;
import com.onnivirtanen.inventory.domain.valueobject.EANBarcode;
import com.onnivirtanen.inventory.domain.valueobject.Price;
import com.onnivirtanen.inventory.domain.entity.ProductEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Product implements Aggregate {

    private UUID id;
    private EANBarcode barcode;
    private Price price;
    private Category category;
    private List<ProductEvent> eventHistory = new ArrayList<>();
}

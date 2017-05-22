package com.example.product;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by trainer3 on 5/22/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "productCategory")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Product.class, name = "product"),
        @JsonSubTypes.Type(value = DigitalProduct.class, name = "media"),
        @JsonSubTypes.Type(value = StreamingProduct.class, name = "stream")
})
public class Product {
    private String sku;
    private String name;
    private int priceInCents;

    public String getProductCategory() {
        return "product";
    }
}
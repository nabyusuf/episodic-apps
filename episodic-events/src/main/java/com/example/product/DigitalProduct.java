package com.example.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by trainer3 on 5/22/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class DigitalProduct extends Product {

    private String fileUrl;

    public DigitalProduct(String sku, String name, int priceInCents, String fileUrl) {
        super(sku, name, priceInCents);
        this.fileUrl = fileUrl;
    }

    public String getProductCategory() {
        return "media";
    }
}

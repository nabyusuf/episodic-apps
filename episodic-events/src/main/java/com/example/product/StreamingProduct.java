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
public class StreamingProduct extends Product {

    private String streamUrl;

    public StreamingProduct(String sku, String name, int priceInCents, String streamUrl) {
        super(sku, name, priceInCents);
        this.streamUrl = streamUrl;
    }

    public String getProductCategory() {
        return "stream";
    }
}

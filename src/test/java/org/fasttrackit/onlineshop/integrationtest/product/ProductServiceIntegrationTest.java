package org.fasttrackit.onlineshop.integrationtest.product;

import org.fasttrackit.onlineshop.integrationtest.steps.ProductTestSteps;
import org.fasttrackit.onlineshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTestSteps productStepSteps;


    @Test
    public void createProduct_whenValidRequest_thenReturnCreatedProduct(){
        productStepSteps.createProduct();

    }
}

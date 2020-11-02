package org.fasttrackit.onlineshop.service;

import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.domain.ProductReview;
import org.fasttrackit.onlineshop.persistence.ProductReviewRepository;
import org.fasttrackit.onlineshop.transfer.productReview.ProductReviewResponse;
import org.fasttrackit.onlineshop.transfer.productReview.SaveProductReviewRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductReviewService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductReviewService.class);

    private final ProductReviewRepository productReviewRepository;

    private final ProductService productService;

    @Autowired
    public ProductReviewService(ProductReviewRepository productReviewRepository, ProductService productService) {
        this.productReviewRepository = productReviewRepository;
        this.productService = productService;
    }

    @Transactional
    public ProductReviewResponse createReview(SaveProductReviewRequest request) {
        LOGGER.info("Creating product review: {}", request);

        ProductReview productReview = new ProductReview();

        Product product = productService.getProduct(request.getProductId());

        productReview.setProduct(product);
        productReview.setContent(request.getContent());

        ProductReview savedReview = productReviewRepository.save(productReview);

        return mapProductReviewResponse(savedReview);

    }

    private ProductReviewResponse mapProductReviewResponse(ProductReview productReview) {
        ProductReviewResponse reviewResponse = new ProductReviewResponse();
        reviewResponse.setId(productReview.getId());
        reviewResponse.setContent(productReview.getContent());
        return reviewResponse;
    }
}

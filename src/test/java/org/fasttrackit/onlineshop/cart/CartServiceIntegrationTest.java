package org.fasttrackit.onlineshop.cart;

import org.fasttrackit.onlineshop.domain.Cart;
import org.fasttrackit.onlineshop.domain.User;
import org.fasttrackit.onlineshop.service.CartService;
import org.fasttrackit.onlineshop.service.UserService;
import org.fasttrackit.onlineshop.steps.UserTestSteps;
import org.fasttrackit.onlineshop.transfer.cart.AddProductToCartRequest;
import org.fasttrackit.onlineshop.transfer.cart.CartResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
public class CartServiceIntegrationTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserTestSteps userTestSteps;


    @Test
    public void addProductToCart_whenValidRequest_thenProductsAddedToCart() {
        User user = userTestSteps.createUser();
        AddProductToCartRequest request = new AddProductToCartRequest();

        request.setUserId(user.getId());
        request.setProductId(1L);

        cartService.addProductToCart(request);

         CartResponse cart = cartService.getCart(request.getUserId());

        assertThat(cart,notNullValue());
        assertThat(cart.getId(),is(request.getUserId()));



    }
}
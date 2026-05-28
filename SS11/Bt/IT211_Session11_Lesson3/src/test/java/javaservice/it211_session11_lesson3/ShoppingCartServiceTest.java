package javaservice.it211_session11_lesson3;


import javaservice.it211_session11_lesson3.entity.Product;
import javaservice.it211_session11_lesson3.entity.ShoppingCart;
import javaservice.it211_session11_lesson3.exception.InsufficientStockException;
import javaservice.it211_session11_lesson3.exception.ProductNotFoundException;
import javaservice.it211_session11_lesson3.repository.CartRepository;
import javaservice.it211_session11_lesson3.repository.ProductRepository;
import javaservice.it211_session11_lesson3.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    private Product product;
    private ShoppingCart cart;

    @BeforeEach
    void setUp() {

        product = new Product(
                "P1",
                "Laptop",
                2000,
                10
        );

        cart = new ShoppingCart("U1");
    }

    @Test
    void addProductToCart_success() {

        when(productRepository.findById("P1"))
                .thenReturn(Optional.of(product));

        when(cartRepository.findByUserId("U1"))
                .thenReturn(Optional.of(cart));

        shoppingCartService.addProductToCart(
                "U1",
                "P1",
                2
        );

        ArgumentCaptor<ShoppingCart> captor =
                ArgumentCaptor.forClass(ShoppingCart.class);

        verify(cartRepository).save(captor.capture());

        ShoppingCart savedCart = captor.getValue();

        assertThat(savedCart.getItems()).hasSize(1);
    }

    @Test
    void addProductToCart_shouldThrowWhenStockNotEnough() {

        when(productRepository.findById("P1"))
                .thenReturn(Optional.of(product));

        assertThatThrownBy(() ->
                shoppingCartService.addProductToCart(
                        "U1",
                        "P1",
                        100
                )
        )
                .isInstanceOf(InsufficientStockException.class);
    }

    @Test
    void updateProductQuantity_shouldThrowWhenProductDeleted() {

        when(productRepository.findById("P1"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                shoppingCartService.updateProductQuantity(
                        "U1",
                        "P1",
                        5
                )
        )
                .isInstanceOf(ProductNotFoundException.class);
    }
}
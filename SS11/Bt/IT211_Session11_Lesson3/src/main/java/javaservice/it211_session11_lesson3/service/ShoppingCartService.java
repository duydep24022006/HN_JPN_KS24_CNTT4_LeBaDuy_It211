package javaservice.it211_session11_lesson3.service;


import javaservice.it211_session11_lesson3.entity.CartItem;
import javaservice.it211_session11_lesson3.entity.Product;
import javaservice.it211_session11_lesson3.entity.ShoppingCart;
import javaservice.it211_session11_lesson3.exception.InsufficientStockException;
import javaservice.it211_session11_lesson3.exception.InvalidQuantityException;
import javaservice.it211_session11_lesson3.exception.ProductNotFoundException;
import javaservice.it211_session11_lesson3.repository.CartRepository;
import javaservice.it211_session11_lesson3.repository.ProductRepository;

public class ShoppingCartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public ShoppingCartService(ProductRepository productRepository,
                               CartRepository cartRepository) {

        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public void addProductToCart(String userId,
                                 String productId,
                                 int quantity) {

        if (quantity <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than 0"
            );
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found"
                        ));

        if (quantity > product.getStock()) {
            throw new InsufficientStockException(
                    "Not enough stock"
            );
        }

        ShoppingCart cart = cartRepository.findByUserId(userId)
                .orElse(new ShoppingCart(userId));

        CartItem existingItem = cart.getItems()
                .stream()
                .filter(item ->
                        item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {

            int newQuantity =
                    existingItem.getQuantity() + quantity;

            if (newQuantity > product.getStock()) {
                throw new InsufficientStockException(
                        "Not enough stock"
                );
            }

            existingItem.setQuantity(newQuantity);

        } else {

            cart.getItems().add(
                    new CartItem(product, quantity)
            );
        }

        cartRepository.save(cart);
    }

    public void updateProductQuantity(String userId,
                                      String productId,
                                      int newQuantity) {

        if (newQuantity <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than 0"
            );
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found"
                        ));

        if (newQuantity > product.getStock()) {
            throw new InsufficientStockException(
                    "Not enough stock"
            );
        }

        ShoppingCart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new CartNotFoundException(
                                "Cart not found"
                        ));

        CartItem item = cart.getItems()
                .stream()
                .filter(i ->
                        i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Item not found in cart"
                        ));

        item.setQuantity(newQuantity);

        cartRepository.save(cart);
    }

    public void removeProductFromCart(String userId,
                                      String productId) {

        ShoppingCart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new CartNotFoundException(
                                "Cart not found"
                        ));

        cart.getItems().removeIf(item ->
                item.getProduct().getId().equals(productId));

        cartRepository.save(cart);
    }
}
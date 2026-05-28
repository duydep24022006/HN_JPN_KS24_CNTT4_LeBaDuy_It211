package javaservice.it211_session11_lesson3.repository;


import javaservice.it211_session11_lesson3.entity.ShoppingCart;

import java.util.Optional;

public interface CartRepository {

    Optional<ShoppingCart> findByUserId(String userId);

    ShoppingCart save(ShoppingCart cart);
}
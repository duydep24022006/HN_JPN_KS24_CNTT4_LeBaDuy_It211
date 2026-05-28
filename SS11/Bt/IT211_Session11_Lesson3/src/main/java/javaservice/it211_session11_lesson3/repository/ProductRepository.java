package javaservice.it211_session11_lesson3.repository;

import javaservice.it211_session11_lesson3.entity.Product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(String id);
}
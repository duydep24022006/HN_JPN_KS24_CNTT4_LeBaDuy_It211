package javaservice.it211_session11_lesson3.entity;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private String userId;

    private List<CartItem> items = new ArrayList<>();

    public ShoppingCart(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public List<CartItem> getItems() {
        return items;
    }
}
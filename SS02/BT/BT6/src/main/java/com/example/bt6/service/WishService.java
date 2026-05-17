package com.example.bt6.service;

import com.example.bt6.model.entity.Item;
import com.example.bt6.model.entity.WishHistory;

import java.util.List;

public interface WishService {
    Item createItem(Item item);
    Item updateItemStatus(
            Long id,
            String status
    );
    String randomWish();
    List<WishHistory> getHistory();

}

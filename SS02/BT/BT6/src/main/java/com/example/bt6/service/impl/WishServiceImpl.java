package com.example.bt6.service.impl;

import com.example.bt6.exception.InvalidWishException;
import com.example.bt6.exception.WishLimitExceededException;
import com.example.bt6.model.entity.Item;
import com.example.bt6.model.entity.WishHistory;
import com.example.bt6.repository.ItemRepository;
import com.example.bt6.repository.WishHistoryRepository;
import com.example.bt6.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {
    private final ItemRepository itemRepository;
    private final WishHistoryRepository wishHistoryRepository;
    private static int remainingWishes=3;
    private void checkWishLimit() {

        if (remainingWishes <= 0) {

            saveHistory(
                    "REQUEST",
                    "FAILED - Hết lượt điều ước"
            );

            throw new WishLimitExceededException(
                    "Thần đèn đã hết lượt ước!"
            );
        }
    }
    private void decreaseWish() {
        remainingWishes--;
    }
    private void saveHistory(
            String action,
            String result
    ) {

        WishHistory history =
                WishHistory.builder()
                        .action(action)
                        .result(result)
                        .createdAt(
                                LocalDateTime.now()
                        )
                        .build();

        wishHistoryRepository.save(history);
    }
    @Override
    public Item createItem(Item item) {

        checkWishLimit();

        if (item.getName() == null
                || item.getName().isBlank()) {

            saveHistory(
                    "CREATE ITEM",
                    "FAILED"
            );

            throw new InvalidWishException(
                    "Tên item không được để trống"
            );
        }

        item.setStatus("NEW");

        Item savedItem =
                itemRepository.save(item);

        decreaseWish();

        saveHistory(
                "CREATE ITEM",
                "SUCCESS"
        );

        return savedItem;
    }

    @Override
    public Item updateItemStatus(
            Long id,
            String status
    ) {

        checkWishLimit();

        Item item = itemRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Không tìm thấy item"
                        )
                );

        item.setStatus(status);

        Item updatedItem =
                itemRepository.save(item);

        decreaseWish();

        saveHistory(
                "UPDATE STATUS",
                "SUCCESS"
        );

        return updatedItem;
    }


    @Override
    public String randomWish() {

        checkWishLimit();

        List<String> messages =
                Arrays.asList(
                        "Bạn sẽ gặp may mắn hôm nay",
                        "Một cơ hội mới sắp đến",
                        "Hãy kiên trì với mục tiêu"
                );

        Random random = new Random();

        String message =
                messages.get(
                        random.nextInt(
                                messages.size()
                        )
                );

        decreaseWish();

        saveHistory(
                "RANDOM MESSAGE",
                "SUCCESS"
        );

        return message;
    }


    @Override
    public List<WishHistory> getHistory() {

        return wishHistoryRepository.findAll();
    }
}

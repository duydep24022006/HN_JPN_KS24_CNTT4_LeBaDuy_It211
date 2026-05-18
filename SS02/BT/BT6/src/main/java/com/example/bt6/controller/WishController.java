package com.example.bt6.controller;


import com.example.bt6.model.dto.reponse.ApiResponse;
import com.example.bt6.model.entity.Item;
import com.example.bt6.model.entity.WishHistory;
import com.example.bt6.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishes")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @PostMapping("/item")
    public ResponseEntity<ApiResponse<Item>>
    createItem(
            @RequestBody Item item
    ) {

        Item savedItem =
                wishService.createItem(item);

        return new ResponseEntity<>(

                ApiResponse.<Item>builder()
                        .success(true)
                        .message(
                                "Tạo item thành công"
                        )
                        .data(savedItem)
                        .status(
                                HttpStatus.CREATED.value()
                        )
                        .build(),

                HttpStatus.CREATED
        );
    }

    @PutMapping("/item/{id}/status")
    public ResponseEntity<ApiResponse<Item>>
    updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {

        Item updatedItem =
                wishService.updateItemStatus(
                        id,
                        status
                );

        return ResponseEntity.ok(

                ApiResponse.<Item>builder()
                        .success(true)
                        .message(
                                "Cập nhật trạng thái thành công"
                        )
                        .data(updatedItem)
                        .status(
                                HttpStatus.OK.value()
                        )
                        .build()
        );
    }

    @GetMapping("/random")
    public ResponseEntity<ApiResponse<String>>
    randomWish() {

        String message =
                wishService.randomWish();

        return ResponseEntity.ok(

                ApiResponse.<String>builder()
                        .success(true)
                        .message(
                                "Lấy thông điệp thành công"
                        )
                        .data(message)
                        .status(
                                HttpStatus.OK.value()
                        )
                        .build()
        );
    }

    @GetMapping("/history")
    public ResponseEntity<
            ApiResponse<List<WishHistory>>
            > getHistory() {

        List<WishHistory> histories =
                wishService.getHistory();

        return ResponseEntity.ok(


                ApiResponse
                        .<List<WishHistory>>builder()
                        .success(true)
                        .message(
                                "Lấy lịch sử thành công"
                        )
                        .data(histories)
                        .status(
                                HttpStatus.OK.value()
                        )
                        .build()
        );
    }
}
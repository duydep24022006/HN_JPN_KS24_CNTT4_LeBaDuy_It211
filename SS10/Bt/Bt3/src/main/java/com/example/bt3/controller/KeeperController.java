package com.example.bt3.controller;

import com.example.bt3.model.dto.request.KeeperRequest;
import com.example.bt3.model.entity.WarehouseKeeper;
import com.example.bt3.service.KeeperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/keepers")
@RequiredArgsConstructor
public class KeeperController {

    private final KeeperService keeperService;

    @PostMapping
    public WarehouseKeeper createKeeper(@RequestBody KeeperRequest request) {
        return keeperService.createKeeper(request);
    }
}

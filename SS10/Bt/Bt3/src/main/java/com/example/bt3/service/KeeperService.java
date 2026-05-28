package com.example.bt3.service;

import com.example.bt3.model.dto.request.KeeperRequest;
import com.example.bt3.model.entity.WarehouseKeeper;

public interface KeeperService {
    WarehouseKeeper createKeeper(KeeperRequest request);
}
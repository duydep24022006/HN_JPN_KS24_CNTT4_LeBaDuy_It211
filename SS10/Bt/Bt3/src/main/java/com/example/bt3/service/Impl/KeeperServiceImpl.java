package com.example.bt3.service.Impl;

import com.example.bt3.model.dto.request.KeeperRequest;
import com.example.bt3.model.entity.WarehouseKeeper;
import com.example.bt3.repository.WarehouseKeeperRepository;
import com.example.bt3.service.KeeperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeeperServiceImpl implements KeeperService {

    private final WarehouseKeeperRepository keeperRepository;

    @Override
    public WarehouseKeeper createKeeper(KeeperRequest request) {
        WarehouseKeeper keeper = new WarehouseKeeper();
        keeper.setFullName(request.getFullName());
        keeper.setStaffCode(request.getStaffCode());

        log.info("Tạo nhân viên kho thành công: staffCode={}", request.getStaffCode());
        return keeperRepository.save(keeper);
    }
}

package com.acl.vbs.services.impls;

import com.acl.vbs.entities.AireStockage;
import com.acl.vbs.repositories.AireStockageRepository;
import com.acl.vbs.services.AireStockageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AireStockageServiceImpl implements AireStockageService{

    private final AireStockageRepository aireStockageRepository;

    public AireStockageServiceImpl(AireStockageRepository aireStockageRepository) {
        this.aireStockageRepository = aireStockageRepository;
    }

    @Override
    public List<AireStockage> list() {
        return aireStockageRepository.findAll();
    }
}

package com.acl.vbs.services;

import com.acl.vbs.entities.AireStockage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AireStockageService {

    List<AireStockage> list();
}

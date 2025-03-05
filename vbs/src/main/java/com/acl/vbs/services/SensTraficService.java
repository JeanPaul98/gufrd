package com.acl.vbs.services;

import com.acl.vbs.entities.SensTrafic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SensTraficService {

    List<SensTrafic> getSensTrafic();
}

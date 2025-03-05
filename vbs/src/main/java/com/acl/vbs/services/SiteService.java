package com.acl.vbs.services;

import com.acl.vbs.entities.Site;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SiteService {

    List<Site> getSites();
}

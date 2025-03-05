package com.acl.vbs.services.impls;

import com.acl.vbs.entities.Site;
import com.acl.vbs.repositories.SiteRepository;
import com.acl.vbs.services.SiteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {

    private final SiteRepository siteRepository;

    public SiteServiceImpl(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public List<Site> getSites() {
        return siteRepository.findAll();
    }
}

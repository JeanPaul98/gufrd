package com.acl.mswauth.service.email;

import com.acl.mswauth.dto.mail.MailInfoDto;
import org.springframework.stereotype.Service;

/**
 * @author kol on 11/7/24
 * @project msw-auth
 */
@Service
public interface EmailService {
    void  sendEmail(MailInfoDto email);
}

package com.acl.mswauth.dto.mail;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kol on 11/7/24
 * @project msw-auth
 */
@Getter
@Setter
public class MailInfoDto {

    private String sendTo;
    private String subject;
    private String messageText;

    public MailInfoDto(String email, String subjet, String message) {
        this.sendTo = email;
        this.subject = subjet;
        this.messageText = message;
    }
}

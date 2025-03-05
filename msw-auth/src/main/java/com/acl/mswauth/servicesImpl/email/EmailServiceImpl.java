package com.acl.mswauth.servicesImpl.email;

import com.acl.mswauth.dto.mail.MailInfoDto;
import com.acl.mswauth.service.email.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author kol on 11/7/24
 * @project msw-auth
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(MailInfoDto dto) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {

            mimeMessageHelper.setTo(dto.getSendTo());
            mimeMessageHelper.setSubject(dto.getSubject());
            mimeMessageHelper.setFrom(this.env.getProperty("spring.mail.username"));
            mimeMessageHelper.setText(dto.getMessageText(), true);

            new Thread(() -> {

                System.out.println("Sending mail...");
                javaMailSender.send(mimeMessage);
                System.out.println("Mail sent successfully...");
            }).start();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

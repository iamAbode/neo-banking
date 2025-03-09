package com.neo.notification.listener;

import com.neo.common.event.AccountCreationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

/**
 * @Author ABODE
 * @Date 2025/03/09 3:30 PM
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AccountCreationNotificationListener {

    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "account-creation")
    public void listen(AccountCreationEvent accountCreationEvent){
        log.info("Got Message from account-creation topic {}", accountCreationEvent);

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springshop@email.com");
            messageHelper.setTo(accountCreationEvent.getEmail().toString());
            messageHelper.setSubject("New Account Opening");
            messageHelper.setText(String.format("""
                            Hi %s,%s

                            Your current account number %s is now opened.
                            
                            Best Regards
                            Neo Bank
                            """,
                    accountCreationEvent.getName().toString(),
                    accountCreationEvent.getSurname().toString(),
                    accountCreationEvent.getAccountNumber()));
        };
        try {
            javaMailSender.send(messagePreparator);
            log.info("Account Opening Notification email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new RuntimeException("Exception occurred when sending mail to abodecyburg@yahoo.com", e);
        }

    }

}
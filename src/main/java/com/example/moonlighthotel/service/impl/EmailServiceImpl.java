package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.exeptions.EmailNotSentException;
import com.example.moonlighthotel.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.example.moonlighthotel.constant.EmailConstant.*;


@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    private static final Logger logger = LogManager.getLogger(Logger.class);

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendEmail(String to, String subject, String text) {

        try {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(EMAIL_SET_FROM);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            javaMailSender.send(message);

            logger.info(EMAIL_SENT_SUCCESSFULLY);

            //not success
        } catch (EmailNotSentException ex) {

            logger.error(EMAIL_ERROR);
        }

    }

}


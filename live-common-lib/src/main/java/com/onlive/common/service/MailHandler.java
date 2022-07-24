package com.onlive.common.service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.onlive.common.vo.SendCodeVo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MailHandler {
    
    private final JavaMailSender sender;
    private final SpringTemplateEngine springTemplateEngine;
    
    public void send(SendCodeVo sendCodeVo) throws MessagingException{
        MimeMessage msg = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        try {
            helper.setTo(sendCodeVo.getSendTo());           //받는사람
            helper.setFrom(sendCodeVo.getSendFrom());       //보내는사람
            helper.setSubject(sendCodeVo.getSubject());     //제목
            helper.setText(sendCodeVo.getContents(), true); //내용

            sender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}

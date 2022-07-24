package com.onlive.common.service;

import java.util.HashMap;
import java.util.Random;

import javax.mail.MessagingException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.onlive.common.config.MessageSourceConfig;
import com.onlive.common.mapper.SendCodeMapper;
import com.onlive.common.vo.SendCodeVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CodeMessageService {

    @Value("${onl.phoneCode.apiKey}")
    private String apiKey;
    @Value("${onl.phoneCode.apiSecret}")
    private String apiSecret;
    @Value("${onl.phoneCode.fromNumber}")
    private String fromNumber;
    @Value("${spring.mail.username}")
    private String fromEmail;
    
    private final SendCodeMapper sendCodeMapper;
    private final MailHandler mailHandler;
    private final MessageSourceConfig messageSource;

    public String getAuthCode() {
        // 인증코드 생성
        Random rd = new Random();
        int authCode = rd.nextInt(999999)+111111;
        return Integer.toString(authCode);
    }

    public int sendCodePhone(SendCodeVo sendCodeVo) {
        // 인증번호 전송
        Message sms = new Message(apiKey, apiSecret);
        HashMap<String, String> params = new HashMap<String, String>();
        
        String textOne = getMessage("message.phoneCode.text1");
        String textTwo = getMessage("message.phoneCode.text2");
        
        params.put("to", sendCodeVo.getSendTo());   // 수신번호
        params.put("from", fromNumber);             // 발신번호
        params.put("type", "SMS");
        params.put("text", textOne + sendCodeVo.getAuthCode() + textTwo);
        params.put("app_version", "test app 1.2");  // application name and version
        
        // 문자전송 코드 - 주석처리 풀면 실제로 문자 전송 됨
        try {
            JSONObject obj = sms.send(params);
        } catch (CoolsmsException e) {
            log.debug(e.getMessage(),e.getCode());
        }
        return sendCodeMapper.sendCodeHistory(sendCodeVo);
    }

    public int sendCodeEmail(SendCodeVo sendCodeVo) throws MessagingException {       
        sendCodeVo.setSendFrom(fromEmail);  //발신자
        sendCodeVo.setSubject(messageSource.getMessage("message.emailCode.subject"));//제목 넣기
        // 내용설정
        String title = getMessage("message.emailCode.title.pw");
        String hello = getMessage("message.emailCode.hello");
        String text1 = getMessage("message.emailCode.text1");
        String tagOpen = getMessage("message.emailCode.Tag.table.open");
        String tableCode = getMessage("message.emailCode.tableCode");
        String tagclose = getMessage("message.emailCode.Tag.table.close");
        String text2 = getMessage("message.emailCode.text2");
        sendCodeVo.setContents(title+sendCodeVo.getSendTo()+hello+text1+tagOpen+tableCode
                +sendCodeVo.getAuthCode()+tagclose+text2);
        // 메일전송
        mailHandler.send(sendCodeVo);

        //메일 전송 기록하기
        sendCodeVo.setSendType("email");
        return sendCodeMapper.sendCodeHistory(sendCodeVo);
    }

    public int searchAuthCode(SendCodeVo sendCodeVo) {
        String inputCode = sendCodeVo.getInputCode();
        int result = 0;
        // 인증번호 비교
        sendCodeVo = sendCodeMapper.searchAuthCode(sendCodeVo); 
        if (sendCodeVo!=null && sendCodeVo.getAuthCode().equals(inputCode)) {
            return result = 1;
        }
        return result;
    }
    
    public String getMessage(String msg) {
        msg = messageSource.getMessage(msg);
        return msg;
    }
}

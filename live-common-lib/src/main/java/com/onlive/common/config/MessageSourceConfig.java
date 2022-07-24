package com.onlive.common.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

//@Service
@Component
public class MessageSourceConfig {
    
    @Autowired
    MessageSource messageSource;
    
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.forLanguageTag(getLocale()));
    }

    public String getMessage(String code,String locale) {
        return messageSource.getMessage(code, null, Locale.forLanguageTag(locale));
    }
    
    private String getLocale() {
        Locale defaultLocale = LocaleContextHolder.getLocale();
        String local = "ko";
        if(defaultLocale.equals(Locale.ENGLISH)) {
            local = "en";
        }
        
        return local;
    }
}

package com.onlive.front.config;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;


import com.onlive.common.service.LoginService;
import com.onlive.common.vo.UserVo;


public class SessionDestoryListener implements ApplicationListener<SessionDestroyedEvent>{

    //@Autowired
    //private LoginService loginService;
    
    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        //String sessionId = event.getId();

        List<SecurityContext> contexts = event.getSecurityContexts();
        if (CollectionUtils.isNotEmpty(contexts)) {
            for (SecurityContext ctx : contexts) {
                Object principal = ctx.getAuthentication().getPrincipal();
                //System.out.println("principal : "+principal+" sessionId : "+sessionId);
                if (principal != null && principal instanceof UserVo) {
                    //loginService.deleteUserLogin(sessionId, (UserVo) principal);
                }
            }
        }
        
    }

}

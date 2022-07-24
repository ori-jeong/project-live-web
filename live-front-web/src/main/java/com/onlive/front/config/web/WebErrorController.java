package com.onlive.front.config.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
//property에 server.error.path가 있으면 그 값을 없을경우 error.path를 사용하고 둘다 없는 경우 /error를 맵핑한다.
//@RequestMapping("${server.error.path:${error.path:/error}}")
public class  WebErrorController implements ErrorController {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
        
        //log.info("httpStatus : "+status+", "+httpStatus);
        mv.setViewName("/error/error");
        mv.addObject("code", status.toString());
        mv.addObject("msg", httpStatus.getReasonPhrase());

        return mv;
    }

}

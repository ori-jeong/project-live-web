package com.onlive.front.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlive.common.service.LiveService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BaseController {
//    @RequestMapping({ "/", "/index" })
//    //@RequestMapping(value = "/", method = RequestMethod.GET)
//    public String strat(HttpServletRequest request,Model model) {
//        System.out.println(" 시작페이지 ");
//        return "index";
//    }
    private final LiveService liveService;
    
    @Value("${onl.video.url}")
    private String liveUrl;
    
    @RequestMapping({ "/", "/index","/home" })
    public ModelAndView stratShop(HttpServletRequest request) { 
        ModelAndView mv = new ModelAndView();
        mv.addObject("live",liveService.liveListHome());    //라이브탭
        mv.addObject("trailer",liveService.trailerListHome());    //예고탭
        mv.addObject("topten",liveService.toptenListHome());    //top10탭
        mv.addObject("url",liveUrl);
        mv.setViewName("/home");
        return mv;
    }
}

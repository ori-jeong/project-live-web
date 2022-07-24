package com.onlive.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlive.common.vo.AddressVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class popController {
    @RequestMapping("/addrPop")
    public ModelAndView getPopUp(AddressVo addr,String index) {
        ModelAndView mv = new ModelAndView();
        
        mv.addObject("addr",addr);
        mv.setViewName("/pop/address_pop");        
        return mv;
    }
}

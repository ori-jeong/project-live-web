package com.onlive.front.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlive.common.service.LiveService;
import com.onlive.common.service.SaleService;
import com.onlive.common.vo.LiveVo;
import com.onlive.common.vo.PdPostVo;
import com.onlive.common.vo.ProductVo;
import com.onlive.common.vo.UserVo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LiveController {
    private final LiveService liveService;
    private final SaleService saleService;
    
    @Value("${onl.video.url}")
    private String liveUrl;
    @Value("${onl.chat.url}")
    private String chatUrl;
    
    /* 라이브 페이지 */
    @RequestMapping("/live")
    public ModelAndView videoPage(@AuthenticationPrincipal UserVo user,@RequestParam String fm) throws ParseException{
        ModelAndView    mv          = new ModelAndView();
        LiveVo          liveVo      = new LiveVo();
        //유저닉네임(회원이면 채팅 가능)
        if(user !=null) {
            mv.addObject("nick",user.getUserNickname());
        }
        liveVo.setLiveId(fm);
        //라이브 정보
        liveVo      = liveService.getLiveInfo(liveVo);
        
        //라이브 시간 참/거짓
        //boolean liveStatus = liveService.liveTimeCheck(liveVo);
  
        mv.addObject("live",liveVo);
        mv.addObject("liveStatus",liveVo.getLiveStatus());
        mv.addObject("liveUrl",liveUrl);
        mv.addObject("chatUrl",chatUrl);
        mv.setViewName("/live/video");
        return mv;
    }
    /* 리플레이 페이지 */
    @RequestMapping("/replay")
    public ModelAndView replayPage(@AuthenticationPrincipal UserVo user,@RequestParam String fm){
        ModelAndView    mv          = new ModelAndView();
        LiveVo          replayVo    = new LiveVo();

        replayVo.setLiveId(fm);
        //뷰 수 업데이트
        liveService.setLiveView(replayVo);
        //라이브 정보
        replayVo = liveService.getReplayInfo(replayVo);

        mv.addObject("replay",replayVo);
        mv.setViewName("/live/replay");
        return mv;
    }

    /* 판매글 페이지 */
    @RequestMapping("/live/item")
    public ModelAndView itemPage(@AuthenticationPrincipal UserVo user,@RequestParam String pd){
        ModelAndView mv         = new ModelAndView();  
        PdPostVo     pdPostVo   = new PdPostVo();
        //판매글 정보
        pdPostVo      = saleService.getSaleBoard(pd);
        //판매 상품 정보
        List<ProductVo>  productVo = saleService.getSaleProductList(pd);

        mv.addObject("post",pdPostVo);
        mv.addObject("products",productVo);
        mv.setViewName("/live/item");
        return mv;
    }
    
    
}

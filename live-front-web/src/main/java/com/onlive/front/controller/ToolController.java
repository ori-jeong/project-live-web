package com.onlive.front.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onlive.common.config.MessageSourceConfig;
import com.onlive.common.service.AdminService;
import com.onlive.common.service.UploadFileService;
import com.onlive.common.vo.CommonApiResponseVo;
import com.onlive.common.vo.LiveVo;
import com.onlive.common.vo.PdPostVo;
import com.onlive.common.vo.ProductVo;
import com.onlive.common.vo.SalesVo;
import com.onlive.common.vo.SellerVo;
import com.onlive.common.vo.UploadFileVo;
import com.onlive.common.vo.UserVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ToolController {
    private final MessageSourceConfig messageSource;
    private final AdminService adminService;
    private final UploadFileService uploadFileService;
    
    @Value("${onl.chat.url}")
    private String chatUrl;
    
    /* 라이브 관리 페이지*/
    @RequestMapping("/tool/live")
    public ModelAndView adminLivePage(@AuthenticationPrincipal UserVo user) {
        ModelAndView    mv          = new ModelAndView();
        mv.addObject("liveList",adminService.getLiveList(user.getUserId()));
        mv.setViewName("/tool/tool_live");
        return mv;
    }
    /* 라이브 채팅 페이지*/
    @RequestMapping("/tool/live/chat")
    public ModelAndView adminLiveChatPage(@AuthenticationPrincipal UserVo user,@RequestParam String live) {
        ModelAndView    mv          = new ModelAndView();
        mv.addObject("liveKey",adminService.getLiveChatInfo(user.getUserId()));
        mv.addObject("liveId",live);
        mv.addObject("chatUrl",chatUrl);
        mv.setViewName("/tool/tool_live_chat");
        return mv;
    }
    /* 라이브 등록 - 수정 페이지*/
    @RequestMapping("/tool/live/create")
    public ModelAndView createLivePage(@AuthenticationPrincipal UserVo user, String live) {
        ModelAndView    mv          = new ModelAndView();
        
        if(live !=null) {
            LiveVo liveVo = new LiveVo();
            liveVo.setSelId(user.getUserId());
            liveVo.setLiveId(live);
            mv.addObject("live",adminService.getLiveInfo(liveVo));
        }
        mv.addObject("category",adminService.getCategoryList());
        mv.addObject("region",adminService.getRegionList());
        mv.addObject("pdPostList",adminService.getOnPdPostList(user.getUserId()));
        mv.setViewName("/tool/tool_live_create");
        return mv;
    }
    /* 라이브 등록 하기 */
    @RequestMapping("/tool/live/create_process")
    @ResponseBody
    public CommonApiResponseVo<String> createLiveProcess(@AuthenticationPrincipal UserVo user,LiveVo live) throws ParseException {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();    
        live.setSelId(user.getUserId());
        int result = adminService.createLive(live);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.live.create"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }
    /* 라이브 수정 하기 */
    @RequestMapping("/tool/live/update_process")
    @ResponseBody
    public CommonApiResponseVo<String> updateLiveProcess(@AuthenticationPrincipal UserVo user,LiveVo live) throws ParseException {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();  
        live.setSelId(user.getUserId());
        int result = adminService.updateLive(live);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.live.update"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }
    /* 라이브 삭제 하기 */
    @RequestMapping("/tool/live/delete_process")
    @ResponseBody
    public CommonApiResponseVo<String> deleteLiveProcess(@AuthenticationPrincipal UserVo user, LiveVo live) {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        live.setSelId(user.getUserId());
        int result = adminService.deleteLive(live);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.live.delete"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }  
    /* 상품 관리 페이지 */
    @RequestMapping("/tool/products")
    public ModelAndView adminProductsPage(@AuthenticationPrincipal UserVo user) {
        ModelAndView    mv          = new ModelAndView();
        List<ProductVo> products = adminService.getProducts(user.getUserId());
        mv.addObject("products", products);
        mv.setViewName("/tool/tool_products");
        return mv;
    }
    /* 상품 등록-수정 페이지 */
    @RequestMapping("/tool/products/create")
    public ModelAndView createProductPage(@AuthenticationPrincipal UserVo user, String pdId) {
        ModelAndView    mv          = new ModelAndView();       
        if(pdId !=null) {
            ProductVo product = new ProductVo();
            product.setSelId(user.getUserId());
            product.setPdId(pdId);
            mv.addObject("pd", adminService.getProductInfo(product));
        }
        mv.setViewName("/tool/tool_products_create");
        return mv;
    }
    
    /* 상품 등록 하기 */
    @RequestMapping("/tool/products/create_process")
    @ResponseBody
    public CommonApiResponseVo<String> createProductProcess(@AuthenticationPrincipal UserVo user,ProductVo product) {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        product.setSelId(user.getUserId());
        int result = adminService.createProduct(product);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.producs.create"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }
    /* 상품 수정 하기 */
    @RequestMapping("/tool/products/update_process")
    @ResponseBody
    public CommonApiResponseVo<String> updateProductProcess(@AuthenticationPrincipal UserVo user,ProductVo product) {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        product.setSelId(user.getUserId());
        int result = adminService.updateProduct(product);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.producs.update"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }   
    /* 상품 삭제 하기 */
    @RequestMapping("/tool/products/delete_process")
    @ResponseBody
    public CommonApiResponseVo<String> deleteProductsProcess(@AuthenticationPrincipal UserVo user, @RequestBody ProductVo product) {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        product.setSelId(user.getUserId());
        int result = adminService.deleteProducts(product);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.producs.delete"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }    
    /* 판매글 관리 페이지*/
    @RequestMapping("/tool/pdpost")
    public ModelAndView adminPdpostPage(@AuthenticationPrincipal UserVo user) {
        ModelAndView    mv          = new ModelAndView();
       
        mv.addObject("pdPostList", adminService.getPdPostList(user.getUserId()));
        mv.setViewName("/tool/tool_pdpost");
        return mv;
    }
    
    /* 판매글 등록-수정 페이지*/
    @RequestMapping("/tool/pdpost/create")
    public ModelAndView createProductPost(@AuthenticationPrincipal UserVo user,String psIndex) {
        ModelAndView    mv          = new ModelAndView();
        if(psIndex !=null) {
            PdPostVo pdPostVo = new PdPostVo();
            pdPostVo.setSelId(user.getUserId());
            pdPostVo.setPsIndex(psIndex);
            mv.addObject("postInfo",adminService.getPdpostInfo(pdPostVo));
        }
        mv.addObject("pdList",adminService.getProductsList(user.getUserId()));
        mv.setViewName("/tool/tool_pdpost_create");
        return mv;
    }
    /* 판매글 등록하기*/
    @RequestMapping("/tool/pdpost/create_process")
    @ResponseBody
    public CommonApiResponseVo<String> createPdPostProcess(@AuthenticationPrincipal UserVo user, @RequestBody PdPostVo pdPostVo) {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        pdPostVo.setSelId(user.getUserId());
        int result = adminService.createPdPost(pdPostVo);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.pdpost.create"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }
    /* 판매글 수정하기*/
    @RequestMapping("/tool/pdpost/update_process")
    @ResponseBody
    public CommonApiResponseVo<String> updaetPdPostProcess(@AuthenticationPrincipal UserVo user, @RequestBody PdPostVo pdPostVo) {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();        
        pdPostVo.setSelId(user.getUserId());
        int result = adminService.updatePdPost(pdPostVo);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.pdpost.update"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }
    /* 이미지 올리기 */
    @RequestMapping("/uploadFileAws")
    @ResponseBody
    public CommonApiResponseVo<UploadFileVo> uploadFileAws(@RequestParam("file") MultipartFile uploadFile) {
        CommonApiResponseVo<UploadFileVo> response = new CommonApiResponseVo<>();
        //uploadFileService.uploadFolderPath(pdPostVo.getPsImg());
        UploadFileVo uploadFileVo = new UploadFileVo();
        try {
            uploadFileVo = uploadFileService.uploadFolderPath(uploadFile);
            adminService.setUploadFile(uploadFileVo);
            response.setData(uploadFileVo);
            response.setResult(true);
        } catch (Exception e) {
            uploadFileVo = new UploadFileVo();
            response.setResult(false);
            log.error(e.getMessage());
        }
        return response;
    }
    
    /* 판매글 삭제 하기 */
    @RequestMapping("/tool/pdpost/delete_process")
    @ResponseBody
    public CommonApiResponseVo<String> deleteCheckPdPostProcess(@AuthenticationPrincipal UserVo user, @RequestBody PdPostVo pdPost) {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        pdPost.setSelId(user.getUserId());
        int result = adminService.deleteCheckPdPost(pdPost);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.producs.delete"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    } 
    
    /* 판매내역 페이지*/
    @RequestMapping("/tool/sale")
    public ModelAndView adminSalePage(@AuthenticationPrincipal UserVo user) {
        ModelAndView    mv          = new ModelAndView();
        mv.addObject("saleList",adminService.getSaleList(user.getUserId()));
        mv.setViewName("/tool/tool_sale");
        return mv;
    }
    /* 주문 취소하기 */
    @RequestMapping("/tool/sale/cancel_order_process")
    @ResponseBody
    public CommonApiResponseVo<String> cancelOrderProcess(@AuthenticationPrincipal UserVo user, @RequestBody SalesVo salesVo) {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        salesVo.setSelId(user.getUserId());
        int result = adminService.cancelOrderProcess(salesVo);
        
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.sale.process"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }
    /* 주문 발주 확인, 발송 지연, 발송 처리하기 */
    @RequestMapping("/tool/sale/change_order_status_process")
    @ResponseBody
    public CommonApiResponseVo<String> changeOrderStatusPro(@AuthenticationPrincipal UserVo user, @RequestBody SalesVo salesVo) {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        salesVo.setSelId(user.getUserId());
        int result = adminService.changeOrderStatus(salesVo);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.sale.process"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }

    /* 설정 페이지*/
    @RequestMapping("/tool/setting")
    public ModelAndView adminSettingPage(@AuthenticationPrincipal UserVo user) {
        ModelAndView    mv          = new ModelAndView();
        mv.addObject("seller", adminService.getSellerInfo(user.getUserId()));
        mv.addObject("streamkey", adminService.getStreamKey(user.getUserId()));
        mv.setViewName("/tool/tool_setting");
        return mv;
    }
    /* 판매자 정보 수정 */
    @RequestMapping("/tool/setting/update_info_process")
    @ResponseBody
    public CommonApiResponseVo<String> updateInfoprocess(@AuthenticationPrincipal UserVo user, SellerVo seller) {
        CommonApiResponseVo<String> response = new CommonApiResponseVo<>();
        seller.setSelId(user.getUserId());
        if(seller.getFileId().equals("")) {
            seller.setFileId(null);
        }
        user.setSelName(seller.getSelName());
        int result = adminService.updateSellerInfo(seller);
        if(result != 0) {
            response.setResult(true);
            response.setMessage(messageSource.getMessage("message.sellerInfo.update"));
        }else {
            response.setResult(false);
            response.setMessage(messageSource.getMessage("message.error"));
        }
        return response;
    }
    /* 스트림키 초기화 */
    @RequestMapping("/tool/setting/updateKey")
    @ResponseBody
    public String updateStreamKey(@AuthenticationPrincipal UserVo user) {
        SellerVo.SellerLiveVo selLive = new SellerVo.SellerLiveVo();
        selLive.setSelId(user.getUserId());
        return adminService.updateStreamKey(selLive);
    }
}

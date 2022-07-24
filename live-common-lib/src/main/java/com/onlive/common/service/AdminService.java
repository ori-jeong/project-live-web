package com.onlive.common.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onlive.common.mapper.AdminMapper;
import com.onlive.common.vo.LiveVo;
import com.onlive.common.vo.OrderVo;
import com.onlive.common.vo.PdPostVo;
import com.onlive.common.vo.ProductVo;
import com.onlive.common.vo.SalesVo;
import com.onlive.common.vo.SellerVo;
import com.onlive.common.vo.UploadFileVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;
    
    public String getStreamKey(String selId) {
        return adminMapper.getStreamKey(selId);
    }
    public int updateSellerInfo(SellerVo seller) {
        return adminMapper.updateSellerInfo(seller);
    }
    public String updateStreamKey(SellerVo.SellerLiveVo selLive) {
       // String stream = RandomStringUtils.randomAlphabetic(10); //randomAlphabetic : 문자+숫자 랜덤 생성
        UUID uuid = UUID.randomUUID(); 
        String stream = "live_"+ uuid.toString();
        selLive.setSelStreamKey(stream);
        adminMapper.updateStreamKey(selLive);
        return stream;
    }
    
    public List<LiveVo> getLiveList(String selId){
        return adminMapper.getLiveList(selId);
    }
    
    public int createLive(LiveVo live) throws ParseException {
        //live id 설정
        LocalDateTime  nowDate = LocalDateTime .now();
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyMMdd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmmss");
        String day = nowDate.format(dayFormat);
        String time = nowDate.format(timeFormat);
        
        int id = Math.abs(Integer.parseInt(day)-Integer.parseInt(time));
        String liveId = "live_"+Integer.toString(id);
        live.setLiveId(liveId);
        
        //상태 설정
        String status = liveStatus(live);
        live.setLiveStatus(status);  
        
        //live.setLiveStartTime(live.getLiveStartDay()+" "+live.getLiveStartTime());
        //live.setLiveEndTime(live.getLiveStartDay()+" "+live.getLiveEndTime());
        
        return adminMapper.createLive(live);
    }
    public int updateLive(LiveVo live) throws ParseException {
        String status = liveStatus(live);
        live.setLiveStatus(status);      
        return adminMapper.updateLive(live);
    }
    //등록 및 수정하는 라이브 날짜로 라이브 상태 변경하기
    public String liveStatus(LiveVo live) throws ParseException {
        LocalDateTime  localDate = LocalDateTime .now();
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat ("yy-MM-dd hh:mm:ss");
        String nowDateTime = dayFormat.format(localDate);
        
        Date today = dateFormat2.parse(nowDateTime);
        Date startDate = dateFormat2.parse(live.getLiveStartTime()+":00");
        Date endDate = dateFormat2.parse(live.getLiveEndTime()+":00");
        
        int start = today.compareTo(startDate);  
        int end = today.compareTo(endDate);
        //1:크다(1) , 같다(0), 작다(-1)
        if(start < 0) {  //현재날짜는 시작 이전 날짜인가? 
            return "0";
        }else if(start>0 && end<0) { //시작날짜 < 현재날짜 > 종료날짜 => 라이브중 //입력한 날짜가 라이브중인 날짜이면
            //라이브중인 게시물이 있는지 확인
            int count =  adminMapper.countLive1Status(nowDateTime);
            if(count>=1) {
                return "3";     //라이브중인 게시물이 있으면 라이브 불가로 상태 변경
            }else {
                return "1";
            }
        }else {         //종료
            return "2";
        }
    }
    public LiveVo getLiveInfo(LiveVo live) {        
        return adminMapper.getLiveInfo(live);
    }
    
    public int deleteLive(LiveVo live) {
        return adminMapper.deleteLive(live);
    }
    
    public int createProduct(ProductVo product) {
        //상품 ID 생성
        LocalDateTime  nowDate = LocalDateTime .now();
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyMMdd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmmss");
        String day = nowDate.format(dayFormat);
        String time = nowDate.format(timeFormat);
        int id = Math.abs(Integer.parseInt(day)-Integer.parseInt(time));
//        for(int i = time.length()-1; i>0;i--) {
//            id+=time.charAt(i);
//        }
        product.setPdId("pd_"+id);
        return adminMapper.createProduct(product);
    }
    
    public int updateProduct(ProductVo product) {
        return adminMapper.updateProduct(product);
    }
    
    public List<ProductVo> getProducts(String userId) {
        return adminMapper.getProducts(userId);
    }
    public List<ProductVo> getProductsList(String selId) {
        return adminMapper.getProductsList(selId);
    }
    public ProductVo getProductInfo(ProductVo product) {
        return adminMapper.getProductInfo(product);
    }
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int deleteProducts(ProductVo product) {
        adminMapper.deletePdPostAdd(product);
        return adminMapper.deleteProducts(product);
    }
    
    
    public List<PdPostVo> getPdPostList(String selId) {
        return adminMapper.getPdPostList(selId);
    }
    public List<Map<String,Object>> getCategoryList() {        
        return adminMapper.getCategoryList();
    }
    public int setUploadFile(UploadFileVo uploadFileVo) {
        return adminMapper.setUploadFile(uploadFileVo);
    }
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int createPdPost(PdPostVo pdPostVo) {
        //게시글id 생성
        LocalDateTime  nowDate = LocalDateTime .now();
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyMMdd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmmss");
        String day = nowDate.format(dayFormat);
        String time = nowDate.format(timeFormat);
        
        int id = Math.abs(Integer.parseInt(day)-Integer.parseInt(time));
        
        String num = adminMapper.getPsIndexNum(day);
        if(num.equals("0")) num = "1";
        pdPostVo.setPsIndex( Integer.toString(id)+num);
        adminMapper.createPdPost(pdPostVo);
        
        return adminMapper.createAddPdList(pdPostVo);
    }
    public int deleteCheckPdPost(PdPostVo pdPost) {
        return adminMapper.deleteCheckPdPost(pdPost);
    }
    public PdPostVo getPdpostInfo(PdPostVo pdPost) {
        return adminMapper.getPdpostInfo(pdPost);
    }
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int updatePdPost(PdPostVo pdPostVo) {
        
        adminMapper.deleteAddPdList(pdPostVo);         //판매글 추가 상품리스트 삭제
        adminMapper.createAddPdList(pdPostVo);         //판매글 추가 상품리스트 등록
        //adminMapper.deletePdPost(pdPostVo);
        return adminMapper.updatePdPost(pdPostVo);  //상품리스트 삭제
    }
        
    public List<PdPostVo> getOnPdPostList(String selId) {
        return adminMapper.getOnPdPostList(selId);
    }
    
    public String createId() {
        LocalDateTime  nowDate = LocalDateTime .now();
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyMMdd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmmss");
        String day = nowDate.format(dayFormat);
        String time = nowDate.format(timeFormat);
        
        int id = Math.abs(Integer.parseInt(day)-Integer.parseInt(time));
        return Integer.toString(id);
    }
    public List<SalesVo> getSaleList(String selId){
        return adminMapper.getSaleList(selId);
    }
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int cancelOrderProcess(SalesVo salesVo) {
        
        //주문 취소 코드 생성
        LocalDateTime  nowDate = LocalDateTime .now();
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyMMdd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmmss");
        String formatedNow = nowDate.format(dayFormat);
        int orderCount = adminMapper.getCancelOrderCount(formatedNow);
        String day = nowDate.format(dayFormat);
        String time = nowDate.format(timeFormat);
        String cancelOrderId = day+time+orderCount;
        
        //주문취소 상태코드 넣기
        salesVo.setOrderStatus("40");            

        //선택한 주무번호(orderId)와 판매글(psIndex)로 총 개수(주문개수 아니고 주문 코드개수)를 구하고
        //총 개수에 주문 개수를 빼서 1이상이면 배송비를 추가하지 않고
        //0이면 배송비를 보여주며 해당 상품 주문 금액에 배송비를 추가해 최종적으로 보여준다
        //orderId, cancelOrderPrice 출력
        List<OrderVo.CancelOrderVo> cancelOrderList = new ArrayList<OrderVo.CancelOrderVo>();
        List<OrderVo.CancelOrderVo> svo = adminMapper.getCancelTotalPrice(salesVo);
        for(int i =0;i<svo.size();i++) {
            OrderVo.CancelOrderVo cancelOrderVo = new OrderVo.CancelOrderVo();
            cancelOrderVo.setCancelOrderId(cancelOrderId);
            cancelOrderVo.setOrderId(svo.get(i).getOrderId());
            cancelOrderVo.setCancelOrderPrice(svo.get(i).getCancelOrderPrice());
            cancelOrderList.add(cancelOrderVo);
        }
        //취소 db 등록
        adminMapper.insertCancelOrderSeller(cancelOrderList);
        //주문 취소로 수정
        return adminMapper.cancelOrderProcess(salesVo);

    }
    public int changeOrderStatus(SalesVo salesVo) {
        return adminMapper.changeOrderStatus(salesVo);
    }
    
    public SellerVo getSellerInfo(String selId) {
        return adminMapper.getSellerInfo(selId);
    }
    public List<Map<String,Object>> getRegionList() {        
        return adminMapper.getRegionList();
    }
    
    public SellerVo.SellerLiveVo getLiveChatInfo(String selId) {
        return adminMapper.getLiveChatInfo(selId);
    }
}

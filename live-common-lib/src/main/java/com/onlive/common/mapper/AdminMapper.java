package com.onlive.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.onlive.common.vo.LiveVo;
import com.onlive.common.vo.OrderVo;
import com.onlive.common.vo.PdPostVo;
import com.onlive.common.vo.ProductVo;
import com.onlive.common.vo.SalesVo;
import com.onlive.common.vo.SellerVo;
import com.onlive.common.vo.UploadFileVo;

@Mapper
public interface AdminMapper {
    public String getStreamKey(String selId);
    public int updateSellerInfo(SellerVo seller);
    public int updateStreamKey(SellerVo.SellerLiveVo selLive);
    public int createLive(LiveVo live);
    public LiveVo getLiveInfo(LiveVo live);
    public int countLive1Status(Map<String,Object> liveMap);
    public int updateLive(LiveVo live);
    public int deleteLive(LiveVo live);
    public List<LiveVo> getLiveList(String selId);
    public int createProduct(ProductVo product);
    public  List<ProductVo> getProducts(String userId);
    public ProductVo getProductInfo(ProductVo product);
    public int updateProduct(ProductVo product);
    public int deletePdPostAdd(ProductVo product);
    public int deleteProducts(ProductVo product);
    public List<PdPostVo> getPdPostList(String selId);
    public int deleteCheckPdPost(PdPostVo pdPost);
    public List<Map<String,Object>> getCategoryList();
    public int setUploadFile(UploadFileVo uploadFileVo);
    public String getPsIndexNum(String day);
    public int createPdPost(PdPostVo pdPostVo);
    public int createAddPdList(PdPostVo pdPostVo);
    public List<ProductVo> getProductsList(String selId);
    public PdPostVo getPdpostInfo(PdPostVo pdPost);
    public int deleteAddPdList(PdPostVo pdPostVo);
    public int deletePdPost(PdPostVo pdPostVo);
    public int updatePdPost(PdPostVo pdPostVo);
    public List<PdPostVo> getOnPdPostList(String selId);
    public List<SalesVo> getSaleList(String selId);
    public int getCancelOrderCount(String formatedNow);
    public int insertCancelOrderSeller(List<OrderVo.CancelOrderVo> cancelOrderList);
    public int cancelOrderProcess(SalesVo salesVo);
    public int changeOrderStatus(SalesVo salesVo);
    public SellerVo getSellerInfo(String selId);
    public List<Map<String,Object>> getRegionList();
    public List<OrderVo.CancelOrderVo> getCancelTotalPrice(SalesVo salesVo);
    public SellerVo.SellerLiveVo getLiveChatInfo(String selId);
}

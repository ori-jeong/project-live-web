package com.onlive.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.onlive.common.mapper.SaleMapper;
import com.onlive.common.vo.ProductVo;
import com.onlive.common.vo.PdPostVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleMapper saleMapper;
    
    /* 판매글 정보 불러오기 */
    public PdPostVo getSaleBoard(String psIndex) {
        return saleMapper.getSaleBoard(psIndex);
    }

    /* 판매 상품 리스트 불러오기 */
    public List<ProductVo> getSaleProductList(String psIndex) {       
        return saleMapper.getSaleProductList(psIndex);
    }
}

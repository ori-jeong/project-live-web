package com.onlive.common.mapper;

import java.util.List;
import com.onlive.common.vo.PdPostVo;
import com.onlive.common.vo.ProductVo;

public interface SaleMapper {
    public PdPostVo getSaleBoard(String psIndex);
    public List<ProductVo> getSaleProductList(String psIndex);
}

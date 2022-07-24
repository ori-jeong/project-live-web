package com.onlive.common.mapper;

import com.onlive.common.vo.SendCodeVo;

public interface SendCodeMapper {
    public int sendCodeHistory(SendCodeVo sendCodeVo);
    public SendCodeVo searchAuthCode(SendCodeVo sendCodeVo);
}

package com.onlive.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CommonApiResponseVo<T>{
    //데이터 처리 후 메세지 출력
    
    @Builder.Default
    private boolean result      = true;
    
    private String  code;
    private String  message;
    
    private T data;
}

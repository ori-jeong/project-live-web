package com.onlive.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data     
@Builder  
@AllArgsConstructor  
@NoArgsConstructor   
public class AddressVo {
    private String userId;
    private Integer addrIndex;
    private String addrName;
    private String addrRecipient;
    private String addrPostcode;
    private String addrRoadaddr;
    private String addrDetailaddr; 
    private String addrContact;
    private String addrYn;
}

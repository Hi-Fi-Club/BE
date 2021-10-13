package com.teamspace.dto.request.spot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserSelectedSpotRequestDTO {

    @JsonProperty("address_name")
    private String addressName;
    @JsonProperty("b_code")
    private String bCode;
    @JsonProperty("h_code")
    private String hCode;
    @JsonProperty("main_address_no")
    private int mainAddressNo;
    @JsonProperty("mountain_yn")
    private String mountainYn; //boolean
    @JsonProperty("region_1depth_name")
    private String regionFirstDepthName;
    @JsonProperty("region_2depth_name")
    private String regionSecondDepthName;
    @JsonProperty("region_3depth_h_name")
    private String regionThirdDepthName;
    @JsonProperty("sub_address_no")
    private int subAddressNo;
    private double x;
    private double y;
}

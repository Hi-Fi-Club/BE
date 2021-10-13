package com.teamspace.dto.response.spot;

import com.teamspace.domain.UserSpot;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserSpotResponseDTO {

    private final String addressName;
    private final String bCode;
    private final String hCode;
    private final int mainAddressNo;
    private final String mountainYn; //boolean
    private final String regionFirstDepthName;
    private final String regionSecondDepthName;
    private final String regionThirdDepthName;
    private final int subAddressNo;
    private final double x;
    private final double y;

    public static UserSpotResponseDTO from(UserSpot userSpot) {
        return UserSpotResponseDTO.builder()
                .addressName(userSpot.getAddressName())
                .bCode(userSpot.getBCode())
                .hCode(userSpot.getHCode())
                .mainAddressNo(userSpot.getMainAddressNo())
                .mountainYn(userSpot.getMountainYn())
                .regionFirstDepthName(userSpot.getRegionFirstDepthName())
                .regionSecondDepthName(userSpot.getRegionSecondDepthName())
                .regionThirdDepthName(userSpot.getRegionThirdDepthName())
                .subAddressNo(userSpot.getSubAddressNo())
                .x(userSpot.getX())
                .y(userSpot.getY())
                .build();
    }
}

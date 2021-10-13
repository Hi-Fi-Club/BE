package com.teamspace.domain;

import com.teamspace.dto.request.spot.UserSelectedSpotRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_spot")
@Builder
public class UserSpot {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressName;
    private String bCode;
    private String hCode;
    private int mainAddressNo;
    private String mountainYn; //boolean
    private String regionFirstDepthName;
    private String regionSecondDepthName;
    private String regionThirdDepthName;
    private int subAddressNo;
    private double x;
    private double y;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static UserSpot createUserSpot(User user, UserSelectedSpotRequestDTO userSelectedSpotDTO) {
        return UserSpot.builder()
                .addressName(userSelectedSpotDTO.getAddressName())
                .bCode(userSelectedSpotDTO.getBCode())
                .hCode(userSelectedSpotDTO.getHCode())
                .mainAddressNo(userSelectedSpotDTO.getMainAddressNo())
                .mountainYn(userSelectedSpotDTO.getMountainYn())
                .regionFirstDepthName(userSelectedSpotDTO.getRegionFirstDepthName())
                .regionSecondDepthName(userSelectedSpotDTO.getRegionSecondDepthName())
                .regionThirdDepthName(userSelectedSpotDTO.getRegionThirdDepthName())
                .subAddressNo(userSelectedSpotDTO.getSubAddressNo())
                .x(userSelectedSpotDTO.getX())
                .y(userSelectedSpotDTO.getY())
                .user(user)
                .build();
    }

    @Override
    public String toString() {
        return "UserSpot{" +
                "id=" + id +
                ", addressName='" + addressName + '\'' +
                ", bCode='" + bCode + '\'' +
                ", hCode='" + hCode + '\'' +
                ", mainAddressNo=" + mainAddressNo +
                ", mountainYn='" + mountainYn + '\'' +
                ", regionFirstDepthName='" + regionFirstDepthName + '\'' +
                ", regionSecondDepthName='" + regionSecondDepthName + '\'' +
                ", regionThirdDepthName='" + regionThirdDepthName + '\'' +
                ", subAddressNo=" + subAddressNo +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

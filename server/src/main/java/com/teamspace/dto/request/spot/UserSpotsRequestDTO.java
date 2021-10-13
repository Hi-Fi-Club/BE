package com.teamspace.dto.request.spot;

import lombok.Getter;

import java.util.List;

@Getter
public class UserSpotsRequestDTO {

    private Long userId;
    private List<UserSelectedSpotRequestDTO> spots;
}

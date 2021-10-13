package com.teamspace.dto.request;

import com.teamspace.dto.request.interest.UserSelectedInterestsRequestDTO;
import com.teamspace.dto.request.spot.UserSelectedSpotRequestDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class UserInterestsAndSpotsRequestDTO {

    private Long userId;
    private List<UserSelectedInterestsRequestDTO> interests;
    private List<UserSelectedSpotRequestDTO> spots;
}

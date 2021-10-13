package com.teamspace.dto.request.interest;

import lombok.Getter;

import java.util.List;

@Getter
public class UserInterestsRequestDTO {

        private Long userId;
        private List<UserSelectedInterestsRequestDTO> interests;
}

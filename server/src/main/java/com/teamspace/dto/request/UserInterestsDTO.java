package com.teamspace.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class UserInterestsDTO {

        private Long userId;
        private List<UserSelectedInterestsDTO> interests;

}

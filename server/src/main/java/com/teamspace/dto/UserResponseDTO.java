package com.teamspace.dto;

import com.teamspace.domain.User;
import com.teamspace.domain.UserInterest;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@RequiredArgsConstructor
public class UserResponseDTO {

    private final Long id;
    private final String nickname;
    private final String email;
    private final String gender;
    private final String ageRange;
    private final String jwtToken;
    private final List<UserInterest> userInterests;

    public static UserResponseDTO of(User user, String jwtToken) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .ageRange(user.getAgeRange())
                .jwtToken(jwtToken)
                .userInterests(user.getUserInterests())
                .build();
    }
}

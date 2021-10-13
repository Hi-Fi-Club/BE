package com.teamspace.dto.response;

import com.teamspace.domain.User;
import com.teamspace.dto.response.interest.DetailCategoryResponseDTO;
import com.teamspace.dto.response.spot.UserSpotResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@RequiredArgsConstructor
public class JWTUserInfoResponseDTO {

    private final Long id;
    private final String nickname;
    private final String email;
    private final String gender;
    private final String ageRange;
    private final List<DetailCategoryResponseDTO> userInterests;
    private final List<UserSpotResponseDTO> userSpots;

    private static List<DetailCategoryResponseDTO> getUserInterests(User user) {
        if (user.getUserInterests() != null) {
            return user.getUserInterests().stream()
                    .map(userInterest -> DetailCategoryResponseDTO.from(userInterest.getInterestDetailCategory()))
                    .collect(Collectors.toList());
        }
        return null;

    }

    private static List<UserSpotResponseDTO> getUserSpots(User user) {
        if (user.getUserSpots() != null) {
            return user.getUserSpots().stream()
                    .map(userSpot -> UserSpotResponseDTO.from(userSpot))
                    .collect(Collectors.toList());
        }
        return null;

    }

    public static JWTUserInfoResponseDTO of(User user) {
        return JWTUserInfoResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .gender(user.getGender())
                .ageRange(user.getAgeRange())
                .userInterests(getUserInterests(user))
                .userSpots(getUserSpots(user))
                .build();
    }
}

package com.teamspace.dto.response.interest;

import com.teamspace.domain.InterestMainCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class MainCategoryResponseDTO {

    private final Long id;
    private final String name;

    public static MainCategoryResponseDTO from(InterestMainCategory interestMainCategory) {
        return MainCategoryResponseDTO.builder()
                .id(interestMainCategory.getId())
                .name(interestMainCategory.getName())
                .build();
    }
}

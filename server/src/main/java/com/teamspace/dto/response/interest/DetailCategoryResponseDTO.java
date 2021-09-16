package com.teamspace.dto.response.interest;

import com.teamspace.domain.InterestDetailCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class DetailCategoryResponseDTO {

    private final Long mainId;
    private final Long detailId;
    private final String name;

    public static DetailCategoryResponseDTO from(InterestDetailCategory interestDetailCategory) {
        return DetailCategoryResponseDTO.builder()
                .mainId(interestDetailCategory.getInterestMainCategory().getId())
                .detailId(interestDetailCategory.getId())
                .name(interestDetailCategory.getName())
                .build();
    }
}

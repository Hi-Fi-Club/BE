package com.teamspace.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProfileDTO {
    private String nickname;

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "nickname='" + nickname + '\'' +
                '}';
    }
}

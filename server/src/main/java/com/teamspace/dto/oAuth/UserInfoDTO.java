package com.teamspace.dto.oAuth;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
public class UserInfoDTO {

    private Long id;
    private LocalDateTime connectedAt;
    private KakaoAccountDTO kakaoAccount;

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "id=" + id +
                ", connectedAt=" + connectedAt +
                ", kakaoAccount=" + kakaoAccount +
                '}';
    }
}

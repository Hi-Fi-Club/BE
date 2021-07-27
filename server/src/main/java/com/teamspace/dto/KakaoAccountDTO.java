package com.teamspace.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
public class KakaoAccountDTO {

    private boolean profileNicknameNeedsAgreement;
    private ProfileDTO profile;
    private boolean hasEmail;
    private boolean emailNeedsAgreement;
    private boolean isEmailValid;
    private boolean isEmailVerified;
    private String email;
    private boolean hasGender;
    private boolean genderNeedsAgreement;
    private String gender;

    @Override
    public String toString() {
        return "KakaoAccountDTO{" +
                "profileNicknameNeedsAgreement=" + profileNicknameNeedsAgreement +
                ", profile=" + profile +
                ", hasEmail=" + hasEmail +
                ", emailNeedsAgreement=" + emailNeedsAgreement +
                ", isEmailValid=" + isEmailValid +
                ", isEmailVerified=" + isEmailVerified +
                ", email='" + email + '\'' +
                ", hasGender=" + hasGender +
                ", genderNeedsAgreement=" + genderNeedsAgreement +
                ", gender='" + gender + '\'' +
                '}';
    }
}

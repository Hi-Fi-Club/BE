package com.teamspace.domain;

import com.teamspace.dto.oAuth.UserInfoDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private String nickname;
    private String email;
    private String gender;
    private String birthday;
    private String ageRange;
    private String accessToken;
    private String phone;

    @OneToMany(mappedBy = "user")
    private List<UserInterest> userInterests = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserSpot> userSpots = new ArrayList<>();

    public void updateUser(String accessToken) {
        this.accessToken = accessToken;
    }

    public void checkUserInterest() {
        if(this.userInterests.size() == 0) {
            this.userInterests = null;
        }
    }

    public static User createUser(UserInfoDTO userInfo, String accessToken) {
        return User.builder()
                .createdAt(userInfo.getConnectedAt())
                .nickname(userInfo.getKakaoAccount().getProfile().getNickname())
                .email(userInfo.getKakaoAccount().getEmail())
                .gender(userInfo.getKakaoAccount().getGender())
                .ageRange(userInfo.getKakaoAccount().getAgeRange())
                .birthday(userInfo.getKakaoAccount().getBirthday())
                .accessToken(accessToken)
                .build();
    }

    public void removeAccessToken() {
        this.accessToken = null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", ageRange='" + ageRange + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", phone='" + phone + '\'' +
                ", userInterests=" + userInterests +
                ", userSpots=" + userSpots +
                '}';
    }
}

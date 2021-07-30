package com.teamspace.service;

import com.teamspace.domain.User;
import com.teamspace.dto.LoginDTO;
import com.teamspace.dto.UserInfoDTO;
import com.teamspace.dto.UserResponseDTO;
import com.teamspace.exception.UserNotFoundException;
import com.teamspace.oauth.kakao.KakaoOauthService;
import com.teamspace.repository.UserRepository;
import com.teamspace.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final KakaoOauthService kakaoOauthService;
    private final UserRepository userRepository;

    private boolean verifyUser(String userEmail) {
        return userRepository.findByEmail(userEmail).isPresent();
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }


    public UserResponseDTO login(LoginDTO loginDTO) {
        String accessToken = kakaoOauthService.getAccessToken(loginDTO.getCode()).getAccessToken();
        UserInfoDTO userInfo = kakaoOauthService.getUserInfo(accessToken);

        if(verifyUser(userInfo.getKakaoAccount().getEmail())) {
            User user = findUserByEmail(userInfo.getKakaoAccount().getEmail());
            user.updateUser(accessToken);
            user.checkUserInterest();
            userRepository.save(user);
            return UserResponseDTO.of(user, JWTUtil.createToken(user.getId(), user.getNickname()));
        }

        User user = User.createUser(userInfo, accessToken);
        userRepository.save(user);
        String jwtToken = JWTUtil.createToken(user.getId(), user.getNickname());

        return UserResponseDTO.of(user, jwtToken);
    }
}

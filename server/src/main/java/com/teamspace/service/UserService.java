package com.teamspace.service;

import com.teamspace.domain.InterestDetailCategory;
import com.teamspace.domain.User;
import com.teamspace.domain.UserInterest;
import com.teamspace.dto.LoginDTO;
import com.teamspace.dto.UserInfoDTO;
import com.teamspace.dto.UserResponseDTO;
import com.teamspace.dto.request.UserInterestsDTO;
import com.teamspace.exception.UserNotFoundException;
import com.teamspace.oauth.kakao.KakaoOauthService;
import com.teamspace.repository.InterestDetailCategoryRepository;
import com.teamspace.repository.UserInterestRepository;
import com.teamspace.repository.UserRepository;
import com.teamspace.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final KakaoOauthService kakaoOauthService;
    private final UserRepository userRepository;
    private final UserInterestRepository userInterestRepository;
    private final InterestDetailCategoryRepository interestDetailCategoryRepository;

    private boolean verifyUser(String userEmail) {
        return userRepository.findByEmail(userEmail).isPresent();
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    private InterestDetailCategory findInterestDetailCategory(Long id) {
        return interestDetailCategoryRepository.findById(id).orElseThrow(RuntimeException::new);
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

    public void logout(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        /*
        TODO : accessToken 만료시키는 로직
         */
        String userInfo = kakaoOauthService.logout(user.getAccessToken());
        System.out.println("userInfo = " + userInfo);
        user.removeAccessToken();
        userRepository.save(user);
         /*
        TODO : html의 세션 만료시키는 로직
         */
    }

    public void mainCategory() {
    }

    public void detailCategory(Long mainId) {
    }

    public void userSelectedInterest(Long userId, UserInterestsDTO userInterestsDTO) {

        User user = findUser(userId);

        if (userInterestsDTO.getInterests() != null) {
            List<UserInterest> userInterests = userInterestsDTO.getInterests().stream()
                    .map(interest -> UserInterest.createUserInterest(findInterestDetailCategory(interest.getDetailId()), user))
                    .collect(Collectors.toList());

            userInterestRepository.saveAll(userInterests);
        }

    }
}

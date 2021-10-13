package com.teamspace.service;

import com.teamspace.domain.InterestDetailCategory;
import com.teamspace.domain.User;
import com.teamspace.domain.UserInterest;
import com.teamspace.dto.LoginDTO;
import com.teamspace.dto.UserInfoDTO;
import com.teamspace.dto.response.UserResponseDTO;
import com.teamspace.dto.request.interest.UserInterestsDTO;
import com.teamspace.dto.response.interest.DetailCategoryResponseDTO;
import com.teamspace.dto.response.interest.MainCategoryResponseDTO;
import com.teamspace.exception.UserNotFoundException;
import com.teamspace.oauth.kakao.KakaoOauthService;
import com.teamspace.repository.InterestDetailCategoryRepository;
import com.teamspace.repository.InterestMainCategoryRepository;
import com.teamspace.repository.UserInterestRepository;
import com.teamspace.repository.UserRepository;
import com.teamspace.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final KakaoOauthService kakaoOauthService;
    private final UserRepository userRepository;
    private final UserInterestRepository userInterestRepository;
    private final InterestMainCategoryRepository interestMainCategoryRepository;
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

    private List<UserInterest> findUserInterests(Long id) {
        return userInterestRepository.findAllByUser(id);
    }

    private List<UserSpot> findUserSpots(Long id) {
        return userSpotRepository.findAllByUser(id);
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
        String userInfo = kakaoOauthService.logout(user.getAccessToken());
        logger.info("userInfo = " + userInfo);
        user.removeAccessToken();
        userRepository.save(user);
    }

    public JWTUserInfoResponseDTO getUserInfo(Long userId) {
        User user = findUser(userId);
        return JWTUserInfoResponseDTO.of(user);
    }

    public List<MainCategoryResponseDTO> mainCategory() {
        return interestMainCategoryRepository.findAll()
                .stream()
                .map(mainCategory -> MainCategoryResponseDTO.from(mainCategory))
                .collect(Collectors.toList());
    }

    public List<DetailCategoryResponseDTO> detailCategory(Long mainId) {
        return interestDetailCategoryRepository.findAllByInterestMainCategory_Id(mainId)
                .stream()
                .map(detailCategory -> DetailCategoryResponseDTO.from(detailCategory))
                .collect(Collectors.toList());
    }

    public void userSelectedInterestCategory(Long userId, UserInterestsDTO userInterestsDTO) {

        User user = findUser(userId);

        if (userInterestsDTO.getInterests() != null) {
            List<UserInterest> userInterests = userInterestsDTO.getInterests().stream()
                    .map(interest -> UserInterest.createUserInterest(findInterestDetailCategory(interest.getDetailId()), user))
                    .collect(Collectors.toList());

            userInterestRepository.saveAll(userInterests);
        }

    }

    public void userSelectedInterestSpot(Long userId) {
    }

    public void userSelectedInterests(Long userId) {
    }
}

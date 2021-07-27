package com.teamspace.controller;

import com.teamspace.dto.LoginDTO;
import com.teamspace.oauth.kakao.KakaoOauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final KakaoOauthService kakaoOauthService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return kakaoOauthService.getAccessToken(loginDTO.getCode());
    }

}

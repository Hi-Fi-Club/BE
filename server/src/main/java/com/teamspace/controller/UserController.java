package com.teamspace.controller;

import com.teamspace.dto.LoginDTO;
import com.teamspace.dto.UserInfoDTO;
import com.teamspace.dto.UserResponseDTO;
import com.teamspace.oauth.kakao.KakaoOauthService;
import com.teamspace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.login(loginDTO));
    }


}

package com.teamspace.controller;

import com.teamspace.dto.LoginDTO;
import com.teamspace.dto.UserInfoDTO;
import com.teamspace.dto.UserResponseDTO;
import com.teamspace.oauth.kakao.KakaoOauthService;
import com.teamspace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/login/kakao")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.login(loginDTO));
    }

    @GetMapping("/api/logout/kakao")
    public ResponseEntity logout(@RequestAttribute Long userId) {
        userService.logout(userId);
        return ResponseEntity.ok().body("OK");
    }

}

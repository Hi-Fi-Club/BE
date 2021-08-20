package com.teamspace.controller;

import com.teamspace.dto.LoginDTO;
import com.teamspace.dto.UserResponseDTO;
import com.teamspace.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "카카오 로그인" , notes = "카카오 OAuth를 통해 로그인한다.")
    @PostMapping("/api/login/kakao")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.login(loginDTO));
    }

    @ApiOperation(value = "카카오 로그아웃" , notes = "카카오 OAuth에서 로그아웃한다.")
    @GetMapping("/api/logout/kakao")
    public ResponseEntity logout(@RequestAttribute Long userId) {
        userService.logout(userId);
        return ResponseEntity.ok().body("OK");
    }

}

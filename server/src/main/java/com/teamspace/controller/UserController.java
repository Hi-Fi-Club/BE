package com.teamspace.controller;

import com.teamspace.dto.LoginDTO;
import com.teamspace.dto.response.UserResponseDTO;
import com.teamspace.dto.request.interest.UserInterestsDTO;
import com.teamspace.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "카카오 로그인" , notes = "카카오 OAuth를 통해 로그인한다.")
    @PostMapping("/login/kakao")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.login(loginDTO));
    }

    @ApiOperation(value = "카카오 로그아웃" , notes = "카카오 OAuth에서 로그아웃한다.")
    @GetMapping("/logout/kakao")
    public ResponseEntity logout(@ApiParam(hidden = true) @RequestAttribute Long userId) {
        userService.logout(userId);
        return ResponseEntity.ok().body("OK");
    }

    @ApiOperation(value = "관심분야 큰 카테고리" , notes = "유저가 선택할 수 있는 관심분야 목록을 클라이언트에 전달한다.")
    @GetMapping("/user/interests/main")
    public ResponseEntity mainCategory() {
        return ResponseEntity.ok().body(userService.mainCategory());
    }

    @ApiOperation(value = "관심분야 세부 카테고리" , notes = "유저가 큰 카테고리를 선택하면, 그에 해당하는 세부 카테고리 목록을 클라이언트에 전달한다.")
    @GetMapping("/user/interests/{mainId}/detail")
    public ResponseEntity detailCategory(@PathVariable Long mainId) {
        return ResponseEntity.ok().body(userService.detailCategory(mainId));
    }

    @ApiOperation(value = "유저가 선택한 관심정보" , notes = "유저가 선택한 세부 카테고리의 목록을 서버에 전달한다.")
    @PostMapping("/user/interests/category")
    public ResponseEntity userSelectedInterestCategory(@RequestBody UserInterestsDTO userInterestsDTO,
                                               @ApiParam(hidden = true) @RequestAttribute Long userId) {
        userService.userSelectedInterestCategory(userId, userInterestsDTO);
        return ResponseEntity.ok().body("OK");
    }

    @ApiOperation(value = "유저가 선택한 관심지역 등록" , notes = "유저가 선택한 관심지역 정보를 서버에 전달한다.")
    @PostMapping("/user/interests/spot")
    public ResponseEntity userSelectedInterestSpot(@ApiParam(hidden = true) @RequestAttribute Long userId) {
        userService.userSelectedInterestSpot(userId);
        return ResponseEntity.ok().body("OK");
    }

    @ApiOperation(value = "유저가 선택한 관심등록" , notes = "유저가 선택한 관심 및 관심지역 정보를 서버에 전달한다.")
    @PostMapping("/user/interests")
    public ResponseEntity userSelectedInterests(@ApiParam(hidden = true) @RequestAttribute Long userId) {
        userService.userSelectedInterests(userId);
        return ResponseEntity.ok().body("OK");
    }

}

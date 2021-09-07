package com.teamspace.oauth;

import com.teamspace.dto.AccessTokenDTO;
import com.teamspace.dto.UserInfoDTO;

public interface Oauth {

    AccessTokenDTO getAccessToken(String code);

    UserInfoDTO getUserInfo(String accessToken);
}

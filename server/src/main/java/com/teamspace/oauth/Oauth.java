package com.teamspace.oauth;

import com.teamspace.dto.oAuth.AccessTokenDTO;
import com.teamspace.dto.oAuth.UserInfoDTO;

public interface Oauth {

    AccessTokenDTO getAccessToken(String code);

    UserInfoDTO getUserInfo(String accessToken);
}

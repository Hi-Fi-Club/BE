package com.teamspace.oauth;

public interface Oauth {

    String getAccessToken(String code);

    String getUserInfo(String accessToken);
}

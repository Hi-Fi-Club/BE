package com.teamspace.oauth.kakao;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KakaoOauthUtil {

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @Value("${kakao.access.token.uri}")
    private String accessTokenUri;

    @Value("${kakao.user.info.uri}")
    private String userInfoUri;

    @Value("${kakao.redirect.uri}")
    private String redirectUri;

    @Value("${kakao.grant.type}")
    private String grantType;


}

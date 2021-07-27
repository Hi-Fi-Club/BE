package com.teamspace.oauth.kakao;

import com.teamspace.oauth.Oauth;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class KakaoOauthService implements Oauth {

    private final KakaoOauthUtil kakaoOauthUtil;
    private final WebClient webClient;
    private final String clientId;
    private final String clientSecret;
    private final String accessTokenUri;
    private final String userInfoUri;
    private final String redirectUri;
    private final String grantType;

    public KakaoOauthService(KakaoOauthUtil kakaoOauthUtil, WebClient.Builder builder) {
        this.kakaoOauthUtil = kakaoOauthUtil;
        this.webClient = builder
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.clientId = kakaoOauthUtil.getClientId();
        this.clientSecret = kakaoOauthUtil.getClientSecret();
        this.accessTokenUri = kakaoOauthUtil.getAccessTokenUri();
        this.userInfoUri = kakaoOauthUtil.getUserInfoUri();
        this.redirectUri = kakaoOauthUtil.getRedirectUri();
        this.grantType = kakaoOauthUtil.getGrantType();
    }



    @Override
    public String getAccessToken(String code) {

        MultiValueMap<String, String> requestData = new LinkedMultiValueMap<>();
        requestData.add("grant_type",grantType);
        requestData.add("client_id", clientId);
        requestData.add("redirect_uri",redirectUri);
        requestData.add("code", code);
        requestData.add("client_secret", clientSecret);

        Optional<String> result = webClient
                .post()
                .uri("https://kauth.kakao.com/oauth/token")
                .body(BodyInserters.fromFormData(requestData))
                .retrieve()
                .bodyToMono(String.class)
                .blockOptional();

        System.out.println(result);
        return "잘 들어왔니?~~";
    }

    @Override
    public String getUserInfo(String accessToken) {
        return null;
    }
}

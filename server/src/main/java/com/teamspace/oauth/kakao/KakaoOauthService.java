package com.teamspace.oauth.kakao;

import com.teamspace.dto.AccessTokenDTO;
import com.teamspace.dto.UserInfoDTO;
import com.teamspace.exception.OauthException;
import com.teamspace.oauth.Oauth;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();
        this.clientId = kakaoOauthUtil.getClientId();
        this.clientSecret = kakaoOauthUtil.getClientSecret();
        this.accessTokenUri = kakaoOauthUtil.getAccessTokenUri();
        this.userInfoUri = kakaoOauthUtil.getUserInfoUri();
        this.redirectUri = kakaoOauthUtil.getRedirectUri();
        this.grantType = kakaoOauthUtil.getGrantType();
    }


    @Override
    public AccessTokenDTO getAccessToken(String code) {

        MultiValueMap<String, String> requestData = new LinkedMultiValueMap<>();
        requestData.add("grant_type", grantType);
        requestData.add("client_id", clientId);
        requestData.add("redirect_uri", redirectUri);
        requestData.add("code", code);
        requestData.add("client_secret", clientSecret);

        return webClient
                .post()
                .uri("https://kauth.kakao.com/oauth/token")
                .body(BodyInserters.fromFormData(requestData))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return response.bodyToMono(String.class).flatMap(error -> {
                        return Mono.error(new OauthException(error, "getAccessToken"));
                    });
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    return response.bodyToMono(String.class).flatMap(error -> {
                        return Mono.error(new OauthException(error, "getAccessToken"));
                    });
                })
                .bodyToMono(AccessTokenDTO.class)
                .blockOptional()
                .orElseThrow(RuntimeException::new);

    }

    @Override
    public UserInfoDTO getUserInfo(String accessToken) {
        System.out.println("accessToken = " + accessToken);
        return webClient
                .post()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return response.bodyToMono(String.class).flatMap(error -> {
                        return Mono.error(new OauthException(error, "getUserInfo"));
                    });
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    return response.bodyToMono(String.class).flatMap(error -> {
                        return Mono.error(new OauthException(error, "getUserInfo"));
                    });
                })
                .bodyToMono(UserInfoDTO.class)
                .blockOptional()
                .orElseThrow(RuntimeException::new);
    }

    public String logout(String accessToken) {
        System.out.println("accessToken = " + accessToken);
        return webClient
                .post()
                .uri("https://kapi.kakao.com/v1/user/logout")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return response.bodyToMono(String.class).flatMap(error -> {
                        return Mono.error(new OauthException(error, "logout"));
                    });
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    return response.bodyToMono(String.class).flatMap(error -> {
                        return Mono.error(new OauthException(error, "logout"));
                    });
                })
                .bodyToMono(String.class)
                .blockOptional()
                .orElseThrow(RuntimeException::new);
    }

    public void additionLogout() {
        webClient
                .get()
                .uri("https://kauth.kakao.com/oauth/logout?client_id=23782940861ed1c764f28841b9f83c80&logout_redirect_uri=http://localhost:8080/login.html");
    }


}

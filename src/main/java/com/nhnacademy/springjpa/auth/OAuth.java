package com.nhnacademy.springjpa.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springjpa.domain.ResidentInfoDto;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:oauth.properties")
@Getter
public class OAuth {
    private final String loginURL = "https://github.com/login/oauth/authorize";
    private final String tokenRequestURL = "https://github.com/login/oauth/access_token";
    private final String userInfoRequestURL = "https://api.github.com/user";
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Value("${oauth.clientId}")
    private String clientId;

    @Value("${oauth.secret}")
    private String clientSecret;

    @Value("${oauth.redirect}")
    private String redirectURL;

    public ResponseEntity<String> requestAccessToken(String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", redirectURL);
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> responseEntity
            = restTemplate.postForEntity(tokenRequestURL, params, String.class);

        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }
        return null;
    }

    public OAuthTokenDto getAccessToken(ResponseEntity<String> response) throws
        JsonProcessingException {
        System.out.println("response.getBody() = " + response.getBody());
        OAuthTokenDto oAuthTokenDto =
            objectMapper.readValue(response.getBody(), OAuthTokenDto.class);

        return oAuthTokenDto;
    }

    public ResponseEntity<String> requestUserInfo(OAuthTokenDto oAuthTokenDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + oAuthTokenDto.getAccess_token());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        ResponseEntity<String> response =
            restTemplate.exchange(userInfoRequestURL, HttpMethod.GET, request, String.class);
        System.out.println("response.getBody() = " + response.getBody());

        return response;
    }

    public ResidentInfoDto getUserInfo(ResponseEntity<String> response)
        throws JsonProcessingException {
        return objectMapper.readValue(response.getBody(), ResidentInfoDto.class);
    }
}

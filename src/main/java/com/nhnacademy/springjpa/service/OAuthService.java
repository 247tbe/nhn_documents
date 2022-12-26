package com.nhnacademy.springjpa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.springjpa.auth.OAuth;
import com.nhnacademy.springjpa.auth.OAuthTokenDto;
import com.nhnacademy.springjpa.domain.ResidentInfoDto;
import com.nhnacademy.springjpa.auth.UserTokenDto;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service("oAuthService")
public class OAuthService {
    private final OAuth oAuth;
    private final ResidentRepository residentRepository;

    public OAuthService(OAuth oAuth, ResidentRepository residentRepository) {
        this.oAuth = oAuth;
        this.residentRepository = residentRepository;
    }

    private ResidentInfoDto getUserInfo(String code) throws JsonProcessingException {
        ResponseEntity<String> accessTokenResponse = oAuth.requestAccessToken(code);
        OAuthTokenDto oAuthToken = oAuth.getAccessToken(accessTokenResponse);
        ResponseEntity<String> userInfoResponse = oAuth.requestUserInfo(oAuthToken);

        return oAuth.getUserInfo(userInfoResponse);
    }

    public ResponseEntity<String> githubLogin(String code) throws IOException {
        ResidentInfoDto user = getUserInfo(code);

//        if (!residentRepository.findByEmail(user.getEmail())) {
//            residentRepository.save(
//                User.builder()
//                    .username(user.getName())
//                    .password(user.getPassword())
//                    .build()
//            );
            return null;
    }

}

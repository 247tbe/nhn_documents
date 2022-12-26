package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.auth.OAuth;
import com.nhnacademy.springjpa.auth.UserTokenDto;
import com.nhnacademy.springjpa.service.OAuthService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OAuthController {
    private final OAuth oAuth;
    private final OAuthService oAuthService;

    public OAuthController(OAuth oAuth, OAuthService oAuthService) {
        this.oAuth = oAuth;
        this.oAuthService = oAuthService;
    }

    @GetMapping("/github")
    public void getGithubAuthURL(HttpServletResponse response) throws Exception {
        response.sendRedirect(oAuth.getLoginURL());
    }

    @GetMapping("/login/oauth/authorize")
    public ResponseEntity<String> callback(
        @RequestParam(name = "code") String code ) throws IOException {
        return oAuthService.githubLogin(code);
    }

}

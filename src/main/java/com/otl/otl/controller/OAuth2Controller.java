package com.otl.otl.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {

    private final OAuth2AuthorizedClientService authorizedClientService;

    public OAuth2Controller(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/token")
    public String getToken(@RegisteredOAuth2AuthorizedClient("kakao") OAuth2AuthorizedClient client) {
        return "Access Token: " + client.getAccessToken().getTokenValue();
    }
}


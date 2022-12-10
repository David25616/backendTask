package com.thryve.backendTask.controller;

import org.springframework.stereotype.Service;

@Service
public class AuthTokenValidatorImplemented implements AuthTokenValidator {


    @Override
    public boolean validateAuthToken(final String authToken) {
        return authToken != null && !authToken.equals("");
    }
}

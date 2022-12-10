package com.thryve.backendTask.controller;

public interface AuthTokenValidator {

    boolean validateAuthToken(String authToken);
}

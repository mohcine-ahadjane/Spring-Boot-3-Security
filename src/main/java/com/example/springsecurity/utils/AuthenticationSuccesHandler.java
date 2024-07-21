package com.example.springsecurity.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class AuthenticationSuccesHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        boolean isAdmin = authentication.getAuthorities().contains("ROLE_ADMIN");
        if(isAdmin)
            setDefaultTargetUrl("/admin/home");
        else
            setDefaultTargetUrl("/user/home");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

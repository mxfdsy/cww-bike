package com.world.cwwbike.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * provider 流程
 */
public class RestAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("33333");
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        System.out.println("provider -supports 2222");
        return false;
    }
}

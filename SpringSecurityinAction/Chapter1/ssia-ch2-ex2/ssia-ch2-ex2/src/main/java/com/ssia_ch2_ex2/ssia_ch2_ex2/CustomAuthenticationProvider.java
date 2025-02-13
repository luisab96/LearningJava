package com.ssia_ch2_ex2.ssia_ch2_ex2;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // authentication logic here
        String username = authentication.getName();
        String password = String.valueOf(
                authentication.getCredentials());

        if("Montse".equals(username) && "049697".equals(password)){
            return new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    List.of());
        } else {
            throw new AuthenticationCredentialsNotFoundException("Error!");
        }
    }

    @Override
    public boolean supports(Class<?> authenticationType){

        //type of the Authentication implementation here
        return UsernamePasswordAuthenticationToken
                .class
                .isAssignableFrom(authenticationType);
    }

}

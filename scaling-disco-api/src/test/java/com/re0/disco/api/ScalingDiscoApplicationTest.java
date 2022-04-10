package com.re0.disco.api;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author fangxi created by 2022/4/10
 */
@SpringBootTest
public class ScalingDiscoApplicationTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @BeforeEach
    public void before() {
        UserDetails user = userDetailsService.loadUserByUsername("admin");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}

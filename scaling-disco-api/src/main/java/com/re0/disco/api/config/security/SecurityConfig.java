package com.re0.disco.api.config.security;

import com.google.common.collect.Sets;
import com.re0.disco.common.annotation.AnonymousAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * @author fangxi created by 2022/4/6
 */
@SpringBootConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationContext applicationContext;
    private final TokenOncePerRequestFilter tokenOncePerRequestFilter;
    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //禁用表单登录，前后端分离用不上
            .formLogin().disable()
            // 禁用 CSRF
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint())
            .accessDeniedHandler(accessDeniedHandler())
            // 防止iframe 造成跨域
            .and()
            .headers()
            .frameOptions()
            .disable()
            // 不创建会话
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            // 静态资源等等
            .antMatchers(
                HttpMethod.GET,
                "/*.html",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/webSocket/**"
            ).permitAll()
            // swagger 文档
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/*/api-docs").permitAll()
            // 标记可以放行的匿名方法
            .antMatchers(getAnonymousUrls().toArray(new String[0])).permitAll()
            // 放行OPTIONS请求
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .apply(securityConfigurerAdapter());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private Set<String> getAnonymousUrls() {
        // 搜寻匿名标记 url： @AnonymousAccess
        RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) applicationContext.getBean("requestMappingHandlerMapping");
        Map<RequestMappingInfo, HandlerMethod> handlerMethodsMap = requestMappingHandlerMapping.getHandlerMethods();
        Set<String> anonymousUrls = Sets.newHashSet();
        handlerMethodsMap.forEach((requestMappingInfo, handlerMethod) -> {
            AnonymousAccess anonymousAccess = handlerMethod.getMethodAnnotation(AnonymousAccess.class);
            if (anonymousAccess != null && requestMappingInfo.getPatternsCondition() != null) {
                anonymousUrls.addAll(requestMappingInfo.getPatternsCondition().getPatterns());
            }
        });
        return anonymousUrls;
    }

    /**
     * 这个接口当用户未通过认证访问受保护的资源
     */
    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpStatus.UNAUTHORIZED.value(), authException == null ? HttpStatus.UNAUTHORIZED.name() : authException.getMessage());
    }

    /**
     * 当认证成功的用户访问受保护的资源，但是权限不够
     */
    private AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> response.sendError(HttpStatus.FORBIDDEN.value(), accessDeniedException == null ? HttpStatus.FORBIDDEN.name() : accessDeniedException.getMessage());
    }

    /**
     * 添加过滤器
     */
    private SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> securityConfigurerAdapter() {
        return new SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
            @Override
            public void configure(HttpSecurity http) throws Exception {
                http.addFilterBefore(tokenOncePerRequestFilter, UsernamePasswordAuthenticationFilter.class);
            }
        };
    }
}

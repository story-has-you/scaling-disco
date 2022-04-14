package com.re0.disco.api.config;

import com.re0.disco.common.toolkit.StringPool;
import com.re0.disco.common.utils.JacksonUtils;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author fangxi created by 2022/4/6
 */
@EnableWebMvc
@SpringBootConfiguration
public class ScalingDiscoWebMvcConfig implements WebMvcConfigurer {
    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路由
        registry.addMapping("/**")
            // 设置允许跨域请求的域名------------修改此行
            .allowedOriginPatterns(StringPool.ASTERISK)
            // 是否允许证书（cookies）
            .allowCredentials(true)
            // 设置允许的方法
            .allowedMethods(StringPool.ASTERISK)
            // 跨域允许时间
            .maxAge(3600);
    }

    /**
     * json配置
     *
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return JacksonUtils.mappingJackson2HttpMessageConverter(true);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

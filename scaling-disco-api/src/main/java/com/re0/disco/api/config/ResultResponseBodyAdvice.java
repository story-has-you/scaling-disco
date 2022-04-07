package com.re0.disco.api.config;

import com.re0.disco.common.result.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

/**
 * @author fangxi created by 2022/4/6
 */
@RestControllerAdvice
public class ResultResponseBodyAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.getMethod() == null) {
            return false;
        }
        return !returnType.getMethod().getReturnType().equals(Result.class);
    }


    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        if (request.getURI().getPath().startsWith("/api/")) {
            Object value = bodyContainer.getValue();
            bodyContainer.setValue(Result.ok(value));
        }
    }
}

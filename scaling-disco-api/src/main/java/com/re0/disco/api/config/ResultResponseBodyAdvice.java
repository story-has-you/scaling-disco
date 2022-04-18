package com.re0.disco.api.config;

import com.re0.disco.common.result.Result;
import org.apache.commons.lang3.StringUtils;
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

    private static final String SCALING_DISCO_WEB = "scaling-disco-web";

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.getMethod() == null) {
            return false;
        }
        return !returnType.getMethod().getReturnType().equals(Result.class);
    }


    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
        String source = request.getHeaders().getFirst("source");
        if (StringUtils.equals(SCALING_DISCO_WEB, source)) {
            Object value = bodyContainer.getValue();
            bodyContainer.setValue(Result.ok(value));
        }
    }
}

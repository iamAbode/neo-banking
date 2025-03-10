package com.neo.common.advice;

import com.neo.common.annotation.WrapResponse;
import com.neo.common.exception.ResourceNotFoundException;
import com.neo.common.response.BaseResponse;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author ABODE
 * @Date 2025/03/08 12:44 PM
 */

@ControllerAdvice(annotations = WrapResponse.class)
public class NeoResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // Apply this advice to all controllers
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  org.springframework.http.MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // If response is already wrapped, return as is
        if (body instanceof BaseResponse) {
            return body;
        }

        return new BaseResponse<>("200", true, body);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<String>> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new BaseResponse<>("404", false, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<String>> handleBadRequest(IllegalArgumentException ex) {
        return new ResponseEntity<>(new BaseResponse<>("400", false, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<BaseResponse<String>> handleDataIntegrityException(Exception ex) {
        return new ResponseEntity<>(new BaseResponse<>("422", false, ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Handle exceptions globally and return appropriate response
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<String>> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(new BaseResponse<>("500", false, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

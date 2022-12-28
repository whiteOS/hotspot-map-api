package com.entire.hotspotmap.system.exception;

import com.entire.hotspotmap.system.Constants;
import com.entire.hotspotmap.system.utils.CommonUtil;
import com.entire.hotspotmap.system.main.web.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResult<?> methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e,
                                                           HttpServletResponse response) {
        CommonUtil.addCrossHeaders(response);
        return new ApiResult<>(Constants.RESULT_ERROR_CODE, "请求方式不正确").setError(e.toString());
    }

    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public ApiResult<?> accessDeniedExceptionHandler(AccessDeniedException e, HttpServletResponse response) {
        CommonUtil.addCrossHeaders(response);
        return new ApiResult<>(Constants.UNAUTHORIZED_CODE, Constants.UNAUTHORIZED_MSG).setError(e.toString());
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ApiResult<?> businessExceptionHandler(BusinessException e, HttpServletResponse response) {
        CommonUtil.addCrossHeaders(response);
        return new ApiResult<>(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ApiResult<?> exceptionHandler(Throwable e, HttpServletResponse response) {
        logger.error(e.getMessage(), e);
        CommonUtil.addCrossHeaders(response);
        return new ApiResult<>(Constants.RESULT_ERROR_CODE, Constants.RESULT_ERROR_MSG).setError(e.toString());
    }

}

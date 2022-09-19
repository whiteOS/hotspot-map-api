package com.entire.hotspotmap.system.security;

import com.entire.hotspotmap.system.Constants;
import com.entire.hotspotmap.system.utils.CommonUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 没有访问权限异常处理
 *
 * @author EleAdmin
 * @since 2020-03-25 00:35:03
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        CommonUtil.responseError(response, Constants.UNAUTHORIZED_CODE, Constants.UNAUTHORIZED_MSG, e.getMessage());
    }

}

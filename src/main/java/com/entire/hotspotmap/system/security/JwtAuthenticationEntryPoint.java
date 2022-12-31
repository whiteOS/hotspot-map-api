package com.entire.hotspotmap.system.security;

import com.entire.hotspotmap.system.Constants;
import com.entire.hotspotmap.system.utils.CommonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        CommonUtil.responseError(response, Constants.UNAUTHENTICATED_CODE, Constants.UNAUTHENTICATED_MSG,
                e.getMessage());
    }

}

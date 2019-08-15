package com.example.demo.shiro.filter;

import com.example.demo.shiro.JWTConstant;
import com.example.demo.shiro.model.JWTToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package： com.example.demo.shiro
 * Author:  hujin
 * Date: 2019/8/7 14:20
 * Description:
 * Version：
 */
public class JWTFilter  extends BasicHttpAuthenticationFilter {


    /**
     * 检测用户请求是否携带jwt头域
     * @param request
     * @param response
     * @return
     */
    @Override
    protected  boolean isLoginAttempt(ServletRequest request, ServletResponse response){
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(JWTConstant.LoginSign);
        return authorization != null;
    }

    /**
     * 服务端登录
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean executeLogin (ServletRequest request,ServletResponse response){
        HttpServletRequest req = (HttpServletRequest)request;
        String head = req.getHeader(JWTConstant.LoginSign);
        JWTToken token = new JWTToken(head,JWTConstant.EXPIRE_TIME);
        getSubject(request,response).login(token);
        return true;
    }

    /**
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 允许跨域
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}

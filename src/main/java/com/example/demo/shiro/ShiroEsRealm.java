package com.example.demo.shiro;

import com.example.demo.pojo.User;
import com.example.demo.service.AuthService;
import com.example.demo.shiro.model.JWTToken;
import com.example.demo.shiro.utils.JWTUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Package： com.example.demo.shiro
 * Author:  hujin
 * Date: 2019/8/7 15:17
 * Description:
 * Version：
 */
public class ShiroEsRealm  extends AuthorizingRealm {

    @Autowired
    private AuthService authService;

    /**
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // todo 权限校验
        return info;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        String token = (String) authcToken.getCredentials();
        String username = JWTUtil.getUserName(token);
        User user = authService.getUser(username);

        if (user != null) {
            if (!JWTUtil.verify(token, username, user.getPassword().toUpperCase())) {
                throw new UnknownAccountException(JWTConstant.CODE_4002);
            }
            return new SimpleAuthenticationInfo(user, token, "realm");
        } else {
            throw new UnknownAccountException(JWTConstant.CODE_4001);
        }
    }

}
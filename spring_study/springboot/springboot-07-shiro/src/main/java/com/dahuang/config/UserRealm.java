package com.dahuang.config;

import com.dahuang.pojo.User;
import com.dahuang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的Realm  extends AuthorizingRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了PrincipalCollection");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前User登陆对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();

        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了AuthenticationToken");

        //用户名、密码， 数据库中取

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        User user = userService.queryUserByName(userToken.getUsername());

        if (user == null) {
            return null;
        }
        //密码认证 ，shiro自己完成
        return new SimpleAuthenticationInfo(user, user.getPwd(), "");
    }
}


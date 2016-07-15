package com.ccit.service;


import com.ccit.mappers.RoleMapper;
import com.ccit.mappers.UserMapper;
import com.ccit.pojo.Role;
import com.ccit.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ShiroRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
    @Inject
    private UserMapper userMapper;
    @Inject
    private RoleMapper roleMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        if(user != null){
            Integer roleid = user.getRoleid();
            Role role = roleMapper.findById(roleid);
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole(role.getRolename());
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
       String username = token.getUsername();
       User user = userMapper.findByUserName(username);
        if(user != null){
            if(!user.getEnable()){
                throw new LockedAccountException("此账号已被禁用");
            }
            return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        }else {
           throw new UnknownAccountException("账号或密码错误");
        }
    }
}

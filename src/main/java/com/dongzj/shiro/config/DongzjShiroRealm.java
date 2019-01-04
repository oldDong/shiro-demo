package com.dongzj.shiro.config;

import com.dongzj.shiro.entity.SysPermission;
import com.dongzj.shiro.entity.SysRole;
import com.dongzj.shiro.entity.UserInfo;
import com.dongzj.shiro.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录认证实现
 * 在认证、授权内部实现机制中都有提到，最终处理都将交给Real进行处理。
 * 可以说，Realm是专用于安全框架的DAO，shiro的认证过程最终会交由Realm执行，这时会调用Realm的getAuthenticationInfo( token )方法
 * 该方法主要执行以下操作：
 * 1、检查提交的进行认证的令牌信息
 * 2、根据令牌信息从数据源（通常为数据库）中获取用户信息
 * 3、对用户信息进行匹配验证
 * 4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例
 * 5、验证失败则抛出AuthenticationException异常信息
 * <p>
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/12/17
 * Time: 14:07
 */
public class DongzjShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限配置-->DongzjShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        for (SysRole role : userInfo.getRoleList()) {
            authenticationInfo.addRole(role.getRole());
            for (SysPermission p : role.getPermissions()) {
                authenticationInfo.addStringPermission(p.getPermission());
            }
        }
        return authenticationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("DongzjShiroRealm.doGetAuthorizationInfo()");
        //获取用户的输入账号
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找User对象
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findByUsername(username);
        if (userInfo == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo,   //用户名
                userInfo.getPassword(),     //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),   //salt = username+salt
                userInfo.getName());    //real name

        return authenticationInfo;
    }
}

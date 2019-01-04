package com.dongzj.shiro.service;

import com.dongzj.shiro.entity.UserInfo;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/12/17
 * Time: 14:46
 */
public interface UserInfoService {

    UserInfo findByUsername(String username);
}

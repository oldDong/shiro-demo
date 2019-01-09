package com.dongzj.shiro.dao;

import com.dongzj.shiro.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/12/17
 * Time: 14:44
 */
public interface UserInfoDao extends CrudRepository<UserInfo, Long> {

    /**
     * 通过username查找用户信息
     *
     * @param username
     * @return
     */
    UserInfo findByUsername(String username);
}

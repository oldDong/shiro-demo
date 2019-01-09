package com.dongzj.shiro.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/12/17
 * Time: 15:26
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    /**
     * 用户查询
     * 权限管理
     *
     * @return
     */
    @GetMapping("/userList")
    @RequiresPermissions("userInfo:view")
    public String userInfo() {
        return "userInfo";
    }

    /**
     * 用户添加
     *
     * @return
     */
    @GetMapping("/userAdd")
    @RequiresPermissions("userInfo:add")
    public String userInfoAdd() {
        return "userInfoAdd";
    }

    /**
     * 用户删除
     *
     * @return
     */
    @GetMapping("/userDel")
    @RequiresPermissions("userInfo:del")
    public String userDel() {
        return "userInfoDel";
    }
}

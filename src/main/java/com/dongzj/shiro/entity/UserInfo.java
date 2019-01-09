package com.dongzj.shiro.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 * <p>
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/12/17
 * Time: 13:44
 */
@Data
@Entity
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -5102840698330323618L;

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 账号
     */
    @Column(unique = true)
    private String username;

    /**
     * 名称（昵称或真实姓名）
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密密码的盐
     */
    private String salt;

    /**
     * 用户状态
     * 0：创建未认证（比如没有激活、没有输入验证码等待）
     * 1：正常状态
     * 2：用户被锁定
     */
    private byte state;

    /**
     * 一个用户具有多个角色
     * FetchType.EAGER：立即从数据库中加载数据
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "uid")})
    private List<SysRole> roleList;

    /**
     * 密码盐
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }
}

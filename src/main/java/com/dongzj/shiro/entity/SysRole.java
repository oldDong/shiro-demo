package com.dongzj.shiro.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 角色信息
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/12/17
 * Time: 13:51
 */
@Data
@Entity
public class SysRole {

    /**
     * 编号
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 角色标识程序中判断使用，如"admin",唯一
     */
    private String role;

    /**
     * 角色描述，UI界面显示使用
     */
    private String description;

    /**
     * 是否可用，如果不可用将不会添加给用户
     */
    private Boolean available = Boolean.FALSE;

    /**
     * 角色  -- 权限关系：多对多关系
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "roleId")})
    private List<SysPermission> permissions;

    /**
     * 一个角色对应多个用户
     */
    @ManyToMany
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<UserInfo> userInfos;
}

shiro 方法级别的权限控制

    1、启用Shiro注解
        ShiroConfig.authorizationAttributeSourceAdvisor()
    
    2、方法上使用shiro注解（参考UserInfoControll）
    
        @RequiresAuthentication     需要用户登录
    
        @RequiresUser   
            认证机制    基于session
            被记忆机制   基于cookie
            
        @RequiresGuest
            与@RequiresUser相反，不能认证也不能被记忆
            
        @RequiresRoles              需要角色
    
        @RequiresPermissions        需要权限
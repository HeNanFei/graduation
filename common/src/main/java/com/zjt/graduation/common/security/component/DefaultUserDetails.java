package com.zjt.graduation.common.security.component;

import com.zjt.graduation.common.service.RolePermissionService;
import com.zjt.graduation.common.service.SysUserRoleService;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
@Component
@Data
@Accessors(chain = true)
public class DefaultUserDetails implements UserDetails {

    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    private List<GrantedAuthority> authorities;

    private String token;

    private long userId;

    private String username;

    private String password;
    /**
     *     账户在数据库的状态
     */
    private Integer status;
    /**
     *     账户过期时间
     */
    private LocalDateTime accountExpireTime;
    /**
     *     密码过期时间
     */
    private LocalDateTime passwordExpireTime;
    /**
     *     账户激活状态
     */
    private Boolean activeStatus;
    /**
     *     账户激活时间
     */
    private LocalDateTime activeTime;
    /**
     *     账户权限
     */
    private List<String> roles;

    private String type;

    public static DefaultUserDetails newInstance(){
        return new DefaultUserDetails();
    }

    public String roleNamesWithComma() {
        return StringUtils.collectionToCommaDelimitedString(roles);
    }

    public long geUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //rolePermissionService.getRolePermissionByRoleId();
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return LocalDateTime.now().isBefore(accountExpireTime);
    }

    /**
     *  账户是否未锁定 1-正常。
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * 密码是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return LocalDateTime.now().isBefore(passwordExpireTime);
    }

    /**
     * 账户是否激活
     */
    @Override
    public boolean isEnabled() {
        return activeStatus;
    }

    public List<String> getRoles() {
        return roles;
    }

    public DefaultUserDetails setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public DefaultUserDetails setUserId(long id) {
        this.userId = id;
        return this;
    }

    public DefaultUserDetails setUsername(String username) {
        this.username = username;
        return this;
    }

    public DefaultUserDetails setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public DefaultUserDetails setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getAccountExpireTime() {
        return accountExpireTime;
    }

    public DefaultUserDetails setAccountExpireTime(LocalDateTime accountExpireTime) {
        this.accountExpireTime = accountExpireTime;
        return this;
    }

    public LocalDateTime getPasswordExpireTime() {
        return passwordExpireTime;
    }

    public DefaultUserDetails setPasswordExpireTime(LocalDateTime passwordExpireTime) {
        this.passwordExpireTime = passwordExpireTime;
        return this;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public DefaultUserDetails setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
        return this;
    }

    public LocalDateTime getActiveTime() {
        return activeTime;
    }

    public DefaultUserDetails setActiveTime(LocalDateTime activeTime) {
        this.activeTime = activeTime;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }
}

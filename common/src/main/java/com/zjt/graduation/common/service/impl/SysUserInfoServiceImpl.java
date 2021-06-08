package com.zjt.graduation.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.config.exception.BadCredentialException;
import com.zjt.graduation.common.dto.rolePermission.RolePermissionRequestDTO;
import com.zjt.graduation.common.dto.rolePermission.RolePermissionResponseDTO;
import com.zjt.graduation.common.dto.sysUserInfo.SysUserInfoResponseDTO;
import com.zjt.graduation.common.dto.sysUserInfo.SysUserInfoServiceRequestDTO;
import com.zjt.graduation.common.dto.sysUserRole.SysUserRoleResponseDTO;
import com.zjt.graduation.common.entity.SysUserInfo;
import com.zjt.graduation.common.enums.sysUserInfo.SysUserInfoEnum;
import com.zjt.graduation.common.mapper.PermissionMapper;
import com.zjt.graduation.common.mapper.SysUserInfoMapper;
import com.zjt.graduation.common.query.ComplexQueryRequest;
import com.zjt.graduation.common.security.component.DefaultUserDetails;
import com.zjt.graduation.common.security.component.DynamicSecurityService;
import com.zjt.graduation.common.security.utils.JwtTokenUtil;
import com.zjt.graduation.common.service.PermissionService;
import com.zjt.graduation.common.service.RolePermissionService;
import com.zjt.graduation.common.service.SysUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjt.graduation.common.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @Author hyh
 * @since 2020-08-22
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements SysUserInfoService, DynamicSecurityService {

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;

    private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    private List<RolePermissionResponseDTO> permissionResponseDTOS;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private SysUserInfoService sysUserInfoService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public UserDetails userLogin(SysUserInfo sysUserInfo) {
        UserDetails userDetails = loadUserByUsername(sysUserInfo.getUsername());
        if (!userDetails.getPassword().equals(sysUserInfo.getPassword())) {
            throw new BadCredentialException();
        }
        return loadUserByUsername(sysUserInfo.getUsername());
    }

    @Override
    public Boolean checkVerifiedCode(String verifiedCode) {
        if(! StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(verifiedCode))){
            return true;
        }
        return false;
    }

    @Override
    public Page<SysUserInfoResponseDTO> getAllUserByList(ComplexQueryRequest<SysUserInfoServiceRequestDTO> requestDTO) {
        QueryWrapper queryWrapper = requestDTO.queryWrapper("t");
        queryWrapper.eq("t.deleted",0);
        return sysUserInfoMapper.getAllUserInfoByPage(requestDTO.getPage(),queryWrapper);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserInfo sysUserInfo = new SysUserInfo();
        if (sysUserInfoService.getOne(new QueryWrapper<SysUserInfo>().eq("username", username)) == null) {
            throw new UsernameNotFoundException(SysUserInfoEnum.USER_LOGIN_ERROR.getContent());
        }

        sysUserInfo = sysUserInfoService.getOne(new QueryWrapper<SysUserInfo>().eq("username", username));

        ComplexQueryRequest<RolePermissionRequestDTO> complexQueryRequest = new ComplexQueryRequest<>();
        complexQueryRequest.setPage(new Page().setSize(10000L));
        //获取角色id
        List<Long> roleIds = sysUserRoleService.querySysUserRoleByUserId(sysUserInfo.getId()).stream().map(n -> n.getRoleId()).collect(Collectors.toList());
        Long[] id = new Long[roleIds.size()];
        complexQueryRequest.setRequest(new RolePermissionRequestDTO().setId(roleIds.toArray(id)));

        //获取角色权限
        List<RolePermissionResponseDTO> userPermission = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roleIds)) {
            for (Long roleId : roleIds) {
                userPermission.addAll(rolePermissionService.getRolePermissionByRoleId(roleId));
            }
        }

        this.permissionResponseDTOS = userPermission;
        //载入初始权限
        loadDataSource();

        String type = sysUserInfo.getType();

        DefaultUserDetails defaultUserDetails = new DefaultUserDetails()
                .setUserId(sysUserInfo.getId())
                .setPassword(sysUserInfo.getPassword())
                .setRoles(sysUserRoleService.querySysUserRoleByUserId(sysUserInfo.getId()).stream().map(SysUserRoleResponseDTO::getRoleName).collect(Collectors.toList()))
                .setActiveStatus(true)
                .setAuthorities(userPermission.stream().map(n -> new SimpleGrantedAuthority(n.getUrl())).collect(Collectors.toList()))
                .setUsername(username)
                .setPasswordExpireTime(LocalDateTime.now().plusYears(33))
                .setAccountExpireTime(LocalDateTime.now().plusYears(33))
                .setActiveTime(LocalDateTime.now());
        defaultUserDetails.setType(type);
        String token = jwtTokenUtil.myGeneratorToken(defaultUserDetails);
        defaultUserDetails.setToken(token);
        return defaultUserDetails;
    }


    //权限初始化，在容器注入相关的类的时候会载入权限，在用户登录的时候亦然
    @Override
    public Map<String, ConfigAttribute> loadDataSource() {
        Map<String, ConfigAttribute> permissionMap = new HashMap<>();
        //如果为空说明容器注入的类没有加载权限
            if (permissionResponseDTOS == null) {
            permissionService.getAllPermissionWithoutPage().stream().forEach(n -> permissionMap.put(n.getUrl(), new SecurityConfig(n.getUrl())));;
            //permissionMapper.getAllPermission().stream().forEach(n -> permissionMap.put(n.getUrl(), new SecurityConfig(n.getUrl())));
        } else {
            //否则返回该用户所具有的权限
            permissionResponseDTOS.forEach(n -> permissionMap.put(String.valueOf(n.getPermissionId()), new SecurityConfig(n.getUrl())));
        }
        //permissionMapper.getAllPermission().stream().forEach(n -> permissionMap.put(String.valueOf(n.getUrl()), new SecurityConfig(String.valueOf(n.getId()))));

        return permissionMap;
    }
    static class CglibTest implements MethodInterceptor {
        
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("before invoke");
            Object o1 = methodProxy.invokeSuper(o, objects);
            System.out.println("after invoke");
            return o1;
        }


    }
    public static  Object any(Class clazz){
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(clazz.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(clazz);
        // 设置方法拦截器
        enhancer.setCallback(new CglibTest());
        // 创建代理类
        return enhancer.create();
    }
    public static void  main(String[] args) {
    }


}


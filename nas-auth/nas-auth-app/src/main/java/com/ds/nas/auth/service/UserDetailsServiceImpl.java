//package com.ds.nas.auth.service;
//
//
//import com.ds.nas.auth.dao.domain.UmsAdmin;
//import com.google.common.collect.Lists;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * 自定义认证
// */
////@Component
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private static final String USERNAME = "admin";
//    private static final String PASSWORD = "$2a$10$YNUV/BtCel2orbhgrxyPJeljdKVty6yTAL.Cj4v3XhwHWXBkgyPYW";
//
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        // 查询用户
//        UmsAdmin umsAdmin = new UmsAdmin();
//        umsAdmin.setUsername(USERNAME);
//        umsAdmin.setPassword(PASSWORD);
//
//        // 默认所有用户拥有 USER 权限
//        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
//        grantedAuthorities.add(grantedAuthority);
//
//        // 用户存在
//        if (umsAdmin != null) {
//            return new User(umsAdmin.getUsername(), umsAdmin.getPassword(), grantedAuthorities);
//        }
//
//        // 用户不存在
//        else {
//            return null;
//        }
//    }
//
//}

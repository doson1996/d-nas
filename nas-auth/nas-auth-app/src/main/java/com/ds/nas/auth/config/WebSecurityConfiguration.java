//package com.ds.nas.auth.config;
//
//import com.ds.nas.auth.service.UserDetailsServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//
///**
// * 安全配置
// */
////@Configuration
////@EnableWebSecurity
////@EnableResourceServer
////@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//  //  @Bean
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return new UserDetailsServiceImpl();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceBean());
//    }
//
//    /**
//     * 用于支持 password 模式
//     *
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers("/user/login");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        /**
//         * 将授权访问配置改为注解方式
//         * @see LoginController#info()
//         */
//        http.exceptionHandling()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
////        http.exceptionHandling()
////                .and()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .authorizeRequests()
////                // 授权访问
////                .antMatchers("/user/info").hasAuthority("USER")
////                .antMatchers("/user/logout").hasAuthority("USER");
//    }
//}

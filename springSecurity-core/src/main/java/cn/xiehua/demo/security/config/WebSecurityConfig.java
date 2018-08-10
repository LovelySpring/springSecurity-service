package cn.xiehua.demo.security.config;

import cn.xiehua.demo.security.auth.LoginAuthenticationProvider;
import cn.xiehua.demo.security.filter.JwtLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by xiehua on 2018/6/18.
 * <p>
 * springSecurity的配置类
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final String TOKEN_PATH = "/auth/login";

    @Autowired
    private LoginAuthenticationProvider loginAuthenticationProvider;

    @Autowired
    AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationManager authenticationManager;

/*    @Autowired
    AuthenticationFailureHandler failureHandler;*/

    /**
     * 身份验证管理生成器
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .and()
                .authorizeRequests()
                // 无需验证 所有用户都可访问
                .antMatchers("/hello").permitAll()
                .antMatchers(TOKEN_PATH).permitAll()
                .and()
                .authorizeRequests()
                // 需要验证
                .antMatchers("/v1/**").authenticated()
                .and()
                //  生成token的拦截器 拦截/auth/login请求
                .addFilterBefore(buildJwtLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 创建jwtLoginFilter对象
     * 用来拦截/auth/login请求 创建token
     *
     * @return
     * @throws Exception
     */
    protected JwtLoginFilter buildJwtLoginFilter() throws Exception {
        JwtLoginFilter jwtLoginFilter = new JwtLoginFilter(TOKEN_PATH, successHandler, null);
        jwtLoginFilter.setAuthenticationManager(this.authenticationManager());
        return jwtLoginFilter;
    }
}

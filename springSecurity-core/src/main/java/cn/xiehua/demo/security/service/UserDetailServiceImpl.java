package cn.xiehua.demo.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by xiehua on 2018/6/18.
 *
 * 自定义用户登录
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);
    /**
     * 根据用户名获取用户信息
     *
     * @param s   用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("the username of login = " + s);
        // 这里需要通过查询数据库  封装一个UserDetails对象
        return new User(s, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}

package cn.xiehua.demo.security.service.impl;

import cn.xiehua.demo.entity.User;
import cn.xiehua.demo.security.model.MyUserDetails;
import cn.xiehua.demo.security.service.IUserDetailService;
import cn.xiehua.demo.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by xiehua on 2018/6/18.
 * <p>
 * 自定义用户登录
 */
@Component
public class UserDetailServiceImpl implements IUserDetailService {
    private Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private IUserService userService;

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("the username of login = " + username);
        // 这里需要通过查询数据库  封装一个UserDetails对象
        User user = userService.getByUsername(username);

        if (null != user) {
            MyUserDetails userDetails = new MyUserDetails(JSONObject.toJSONString(user));
            return userDetails;
        }
        return null;
        // return new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("xiehua"));
    }
}

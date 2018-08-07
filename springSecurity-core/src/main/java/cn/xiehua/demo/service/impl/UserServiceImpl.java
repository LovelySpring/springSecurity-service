package cn.xiehua.demo.service.impl;

import cn.xiehua.demo.entity.User;
import cn.xiehua.demo.mapper.UserMapper;
import cn.xiehua.demo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserServiceImpl
 *
 * @author xiehua
 * @date 2018/08/06
 */
@Service
public class UserServiceImpl implements IUserService{
    @Resource
    private UserMapper userMapper;

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.queryByUsername(username);
    }
}

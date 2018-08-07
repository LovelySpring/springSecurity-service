package cn.xiehua.demo.service;

import cn.xiehua.demo.entity.User;

/**
 * Created by xiehua on 2018/8/6.
 */
public interface IUserService {
    User getById(Integer id);

    User getByUsername(String username);
}

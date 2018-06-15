package cn.xiehua.demo.controller;

import cn.xiehua.demo.dto.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiehua
 * @date 2018/06/15
 *
 */
@RestController
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> getUsers(@RequestParam(value = "name") String name) {
        System.out.println(name);
        List users = new ArrayList();
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
        users.add(user);
        return users;
    }
}

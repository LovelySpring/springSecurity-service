package cn.xiehua.demo.controller;

import cn.xiehua.demo.entity.User;
import cn.xiehua.demo.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xiehua
 * @date 2018/06/15
 */
@Controller
@RequestMapping(value = "/v1/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService userService;

    /**
     * 通过id获取用户信息
     *
     * @param id     用户id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getById(@PathVariable(value = "id") Integer id) {
        User user = userService.getById(id);
        return JSONObject.toJSONString(user);
    }
}

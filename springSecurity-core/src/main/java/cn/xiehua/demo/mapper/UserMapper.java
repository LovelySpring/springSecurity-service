package cn.xiehua.demo.mapper;

import cn.xiehua.demo.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by xiehua on 2018/8/6.
 */
public interface UserMapper {
    User getById(@Param(value = "id") Integer id);
}

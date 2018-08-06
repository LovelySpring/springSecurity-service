package cn.xiehua.demo.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * UserRespDTO
 *
 * @author xiehua
 * @date 2018/08/06
 */
@Getter
@Setter
public class User {
    private Integer id;

    private String username;

    private String showName;

    private String password;

    private String phone;

    private Short sex;

    private String avatar;

    private String email;

    private Date createTime;

    private Date updateTime;

    private Short isDelete;
}

package cn.xiehua.demo.security.model;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Set;

/**
 * Created by xiehua on 2018/6/18.
 *
 * 自定义userDetails对象
 */
public class MyUserDetails implements UserDetails, CredentialsContainer{
    private Integer id;

    private String username;

    private String showname;

    private String password;

    public MyUserDetails(String userJson) {
        Assert.notNull(userJson);
        JSONObject jsonObject = JSONObject.parseObject(userJson);
        this.id = jsonObject.getInteger("id");
        this.username = jsonObject.getString("username");
        this.showname = jsonObject.getString("showname");
        this.password = jsonObject.getString("password");
    }

    public MyUserDetails(String username, String showname, String password) {
        this.username = username;
        this.showname = showname;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void eraseCredentials() {
        password = null;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public String getShowname() {
        return this.showname;
    }
}

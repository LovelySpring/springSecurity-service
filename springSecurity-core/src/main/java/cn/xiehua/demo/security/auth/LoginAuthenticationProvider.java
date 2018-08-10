package cn.xiehua.demo.security.auth;

import cn.xiehua.demo.common.Constans;
import cn.xiehua.demo.security.model.MyUserDetails;
import cn.xiehua.demo.security.service.IUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 自定义认证
 * 自定义AuthenticationProvider的实现类
 *
 * @author xiehua
 * @date 2018/08/07
 */
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
    private static final Logger log = LoggerFactory.getLogger(LoginAuthenticationProvider.class);

    @Autowired
    private IUserDetailService userDetailService;

    /**
     * 具体的认证逻辑
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        log.info("username: {}", username);
        MyUserDetails userDetails = (MyUserDetails) userDetailService.loadUserByUsername(username);
        if (null == userDetails) {
            log.info("auth user login fail, username: {}", username);
            throw new BadCredentialsException(Constans.EXCEPTION_BAD_CREDENTIALS);
        }
        String password = userDetails.getPassword();
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        if (!password.equals(encoder.encodePassword(authentication.getCredentials().toString(), null))) {
            log.info("auth user login fail, username: {}", username);
            throw new BadCredentialsException(Constans.EXCEPTION_BAD_CREDENTIALS);
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}

package cn.xiehua.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiehua
 * @date 2018/06/15
 */
@SpringBootApplication
@RestController
public class SecurityApplication {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "<h1>HELLO WORLD</h1>";
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}

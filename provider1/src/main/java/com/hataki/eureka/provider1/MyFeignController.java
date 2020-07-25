package com.hataki.eureka.provider1;

import com.hataki.eureka.userapi.UserApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hataki
 * @Date: 2020/7/25
 * Time: 11:00
 * description:
 * 实现引入的user-api接口
 */
@RestController
public class MyFeignController implements UserApi {

    @Override
    public String isAlive() {
        return "This is feign interface !" + Math.random()*100 + " times you have invoking . ";
    }

    @Override
    public String fingById() {
        return "10000:" + Math.round(Math.random()*100) ;
    }

    @Override
    public String fingByName(String name) {
        return "NoOne called ： " + name ;
    }


}

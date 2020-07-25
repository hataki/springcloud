package com.hataki.eureka.userapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: hataki
 * @Date: 2020/7/25
 * Time: 11:02
 * description:
 *
 * 创建一个用户的接口
 */
@RequestMapping("/user")
public interface UserApi {


    @RequestMapping("/isAlive")
    public String isAlive();


    @RequestMapping(value = "/isAlive",method = RequestMethod.GET)
    public String fingById();


    @RequestMapping(value = "/isAlive",method = RequestMethod.POST)
    public String fingByName(String name );
}

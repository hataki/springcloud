package com.hataki.eureka.comsumer1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;

/**
 * @Author: hataki
 * @Date: 2020/7/25
 * Time: 13:38
 * description:
 *
 * openFeign 远程调用的实现--接口方式的实现：
 * **** caution : Feign/Ribbon/Hystrix 均是在【consumer】端进行实现的 *****
 * install一个userApi的接口，在provider端继承并实现这个接口，
 * comsumer实现FeignClient注解，service需要继承userApi的接口，
 * 在controller请求service.api
 *
 */
@RestController
public class FeignController {

    @Autowired
    FeignService feignService ;


    @RequestMapping(value = "/isAlive",method = RequestMethod.POST)
    public String isAlive(){
        return feignService.isAlive();
    }


   /**
    *  get请求需要注明请求方式
    **/
   @GetMapping("/fingById")
    public String fingById(){
       return feignService.fingById();
   }

   @PostMapping("/fingByName")
    public String fingByName(@RequestParam("name") String name ){
       return feignService.fingByName(name);
   }
}

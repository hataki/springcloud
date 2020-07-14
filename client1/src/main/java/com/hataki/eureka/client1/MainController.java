package com.hataki.eureka.client1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: hataki
 * @Date: 2020/7/14
 * Time: 13:57
 * description:
 */
@Controller
public class MainController {

    @Autowired
    /**
     * cause of netflex closed source of eureka
     * springcloud supplied a new abstract interface
     *
     * */
    private DiscoveryClient client ;

    @GetMapping("/hi")
    public String getHi(){
        return "Hi!";
    }

    @GetMapping("/client")
    public String getClient(){
        List<String> services = client.getServices();
        for(String str : services){
            System.out.println(str);
        }
        return "Service!";
    }




}

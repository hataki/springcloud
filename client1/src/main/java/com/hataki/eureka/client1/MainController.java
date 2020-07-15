package com.hataki.eureka.client1;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: hataki
 * @Date: 2020/7/14
 * Time: 13:57
 * description:
 */
@RestController
public class MainController {

    /**
     * cause of netflex closed source of eureka
     * springcloud supplied a new abstract interface
     *
     * */
    @Autowired
    private DiscoveryClient client ;

    @Autowired
    private EurekaClient eurekaClient ;

    @Autowired
    private LoadBalancerClient lb ;



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


    @GetMapping("/client2")
    public String getClient2() {

        List<InstanceInfo> eurekaServer = eurekaClient.getInstancesByVipAddress("EurekaServer", false);


        for (InstanceInfo instanceInfo : eurekaServer) {

            System.out.println(instanceInfo.getStatus());
            String s = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/eureka";
        }
        return "Service2!";
    }




}

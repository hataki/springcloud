package com.hataki.eureka.client1;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: hataki
 * @Date: 2020/7/14
 * Time: 13:57
 * description:
 *
 * Query for all instances
 *  -- GET /eureka/v2/apps
 *  -- HTTP Code: 200 on success Output: JSON/XML
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

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient ;

    /**
     * ribbon 的负载均衡
     */
    @Autowired
    private LoadBalancerClient lb ;

    @Autowired
    private MyHealthStatusService myHealthStatusService ;

    @GetMapping("/health")
    public String getHealth(@RequestParam("status") boolean status ){
        myHealthStatusService.setStatus(status);
        return myHealthStatusService.getStatus();
    }

    @GetMapping("/hi")
    public String getHi(){
        return "Hi!";
    }

    @GetMapping("/client")
    public String getClient(){

        /**
         * 1.获取实现类的描述。
         * 2.获取所有服务实例id。
         * 3.通过服务id查询服务实例信息列表。
         */
        String description = client.description();
        List<String> services =  client.getServices();
        List<ServiceInstance> serviceInstances =  client.getInstances("EurekaServer");

        System.out.println(description);
        for(String str : services){
            System.out.println(str);
        }
        for(ServiceInstance sic : serviceInstances){

            System.out.println("host : " + sic.getHost());
            System.out.println("port : " + sic.getPort());
            System.out.println("uri : " + sic.getUri());
            System.out.println("serviceId  : " + sic.getServiceId());
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


    @GetMapping("/client3")
    public String getClient3(){

        return "1";
    }



}

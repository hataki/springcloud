package com.hataki.eureka.client1;


import com.hataki.eureka.client1.entity.Batman;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private RestTemplate restTemplate ;

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


    @GetMapping("/getRest")
    @ResponseBody
    public String getRestTemplate(){
        String url ="http://EurekaClient/hi";
        String respStr = restTemplate.getForObject(url, String.class);
        return respStr ;
    }

    @GetMapping("/getRestComsumer")
    @ResponseBody
    public String getRestTemplateComsumer(){
        String url ="http://EurekaProvider/getComsumer1";
        String respStr = restTemplate.getForObject(url, String.class);
        return respStr ;
    }


    @GetMapping("/getMap")
    @ResponseBody
    public String getMap(){
        String url ="http://EurekaProvider/getMap";
        ResponseEntity<Map> entity = restTemplate.getForEntity(url,Map.class);
        return "respStr: "+ entity.getBody() ;
    }



    @GetMapping("/getObject")
    @ResponseBody
    public String getObject(){
        String url ="http://EurekaProvider/getObject";
        ResponseEntity<Batman> entity = restTemplate.getForEntity(url,Batman.class);
        System.out.println(entity.getStatusCode());
        System.out.println(entity.getStatusCodeValue());
        System.out.println(entity.getHeaders());
        System.out.println(entity.getClass());
        return "respStr: "+ ToStringBuilder.reflectionToString(entity.getBody());
    }

    /**
     * 传参调用
     * 1.使用占位符
     * 2.使用map
     * 3.直接返回对象
     */
    @GetMapping("/getByParam")
    @ResponseBody
    public String getByParam(){
        String url ="http://EurekaProvider/getByParam?name={1}";
        ResponseEntity<Batman> entity = restTemplate.getForEntity(url,Batman.class,"hehehe");
        return "respStr: "+ ToStringBuilder.reflectionToString(entity.getBody());
    }

    @GetMapping("/getByParamMap")
    @ResponseBody
    public String getByParamMap(){
        String url ="http://EurekaProvider/getByParamMap?name={name}";
        Map<String,String> map = Collections.singletonMap("name","WonderWomen");
        ResponseEntity<Batman> entity = restTemplate.getForEntity(url,Batman.class,map);
        return "respStr: "+ ToStringBuilder.reflectionToString(entity.getBody());
    }

    @GetMapping("/getByParamObject")
    @ResponseBody
    public String getByParamObject(){
        String url ="http://EurekaProvider/getByParamObject";
        Map<String,String> map = Collections.singletonMap("name","WonderWomen");
        Batman batman = restTemplate.getForObject(url,Batman.class,map);
        return batman.toString();
    }

    @PostMapping("/postParam")
    @ResponseBody
    public String postParam(){
        String url ="http://EurekaProvider/postParam";
        Map<String,String> map = Collections.singletonMap("name","WonderWomen");
        Batman batman = restTemplate.postForObject(url,map,Batman.class);
        return batman.toString();
    }

    @PostMapping("/postLocation")
    @ResponseBody
    public String postLocation(HttpServletResponse httpResponse) throws Exception{
        String url ="http://EurekaProvider/postLocation";
        Map<String,String> map = Collections.singletonMap("name","WonderWomen");
        URI location = restTemplate.postForLocation(url,map,Batman.class);
        httpResponse.sendRedirect(location.toURL().toString());
        return location.toString();
    }


}

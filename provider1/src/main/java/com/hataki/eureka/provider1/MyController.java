package com.hataki.eureka.provider1;

import com.hataki.eureka.provider1.entity.Batman;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MyController {


    @RequestMapping("/getComsumer1")
    public String getComsumer1(){
        return "this is comsumer1 : " + Math.random()*100 ;
    }


    @RequestMapping("/getMap")
    public Map<String,String> getMap(){
        Map<String,String> map = new HashMap<>(8);
        map.put("name","client2");
        return map;
    }

    @RequestMapping("/getObject")
    public Batman getObject(){
        Batman batman = new Batman() ;
        batman.setAge("35");
        batman.setAttribute("hero");
        batman.setName("Blues Wane");
        batman.setColor("black");
        batman.setGender("male");
        return batman;
    }


    @RequestMapping("/getByParam")
    public Batman getByParam(String name ){
        Batman batman = new Batman() ;
        batman.setName(name);
        return batman ;
    }

    @RequestMapping("/getByParamMap")
    public Batman getByParamMap(Map<String,String> map){

        Batman batman = new Batman() ;
        batman.setName(map.get("name"));
        return batman ;
    }

    @RequestMapping("/getByParamObject")
    public Batman getByParamObject(){
        Batman batman = new Batman() ;
        batman.setAge("22");
        batman.setAttribute("hero");
        batman.setName("blues");
        batman.setColor("blue");
        batman.setGender("male");
        return batman;
    }


    @PostMapping("/postParam")
    public Batman postParam(@RequestBody String name ){
        System.out.println("name:" + name);
        Batman batman = new Batman() ;
        batman.setAge("22");
        batman.setAttribute("cat");
        batman.setName("catWomen");
        batman.setColor("red");
        batman.setGender("female");
        return batman;

    }


}

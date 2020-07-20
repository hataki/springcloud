package com.hataki.eureka.client2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {


    @RequestMapping("/hi")
    public String getHi(){
        return "This is client2 , u get it !";
    }


}

package com.hataki.eureka.comsumer1;

import com.hataki.eureka.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * @Author: hataki
 * @Date: 2020/7/25
 * Time: 13:37
 * description:
 * @FeignClient("name = xxxx") this distinct sign 'xxx' should write the provider-service which have erolled eureka
 * name 后面的值 填写在eureka服务提供方注册的serviceId
 */
@Service
@FeignClient(name = "EurekaProvider")
public interface FeignService extends UserApi {

}

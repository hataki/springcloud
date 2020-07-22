package com.hataki.eureka.client1;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @Author: hataki
 * @Date: 2020/7/22
 * Time: 9:21
 * description: restTemplate 拦截器
 * 添加到resttemplate中
 *
 */
public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        System.out.println("拦截啦！！！");
        System.out.println(httpRequest.getURI());

        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, bytes);

        System.out.println(response.getHeaders());

        return response;
    }
}

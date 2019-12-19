package com.southwind.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.southwind.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@RestController
@RequestMapping("/ribbon")
public class RibbonHandler {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/findAll")
    public Collection<Student> findAll(){
        return
                restTemplate.getForObject("http://provider/student/findAll", Collection.class);
    }
    @GetMapping("/index")
    @HystrixCommand(fallbackMethod = "indexError")//熔断函数声明
    public String index(){
        return
                restTemplate.getForObject("http://provider/student/index",String.class);
    }

    public String indexError() {//熔断函数要与所调用函数的参数，返回值一致！
        return "index故障熔断";
    }
}
/*
1.什么是熔断器？
熔断，就是断开与服务器的连接，熔断器是在服务不可用的时候主动断开，以免造成更多的雪崩效应，他是保护服务高可用的最后一道防线。
2.为什么需要熔断器？
为保证服务高可用，最先想到的是服务集群，但集群并不能完全的保证服务高可用，
当某个服务出现故障时，在负载均衡的时候可能多次被调用到，调用方由于无法得到调用结果，会出现请求超时会其他异常，这时候如果不及时的熔断服务，
就有可能会有更多的调用者去调用已经出现故障的服务节点，造成大量调用失败，甚至引发联级故障的雪崩。
 */
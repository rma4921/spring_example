package com.estsoft.demo.controller;

import com.estsoft.demo.service.HelloService;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    // service 메소드 호출

    // @Autowired // Dependency Injection 의존성 주입
    private final HelloService helloService;
    // HelloService에서 @Service 어노테이션을 사용하여 new로 객새를 생성하지 않아도 된다.
    // 스프링에서 객체 관리

    public HelloController(HelloService helloService) { // Dependency Injection 의존성 주입
        // HelloService helloService = new HelloController(); // 일반적인 흐름, 제어
        this.helloService = helloService; // 제어의 역전(IoC)
    }

    @GetMapping("/hello")
    public String hello() {
        return helloService.sayHello();
    }
}

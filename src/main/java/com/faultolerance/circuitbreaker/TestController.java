package com.faultolerance.circuitbreaker;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

//import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test1")
    @CircuitBreaker(name = "test1service", fallbackMethod = "fallback")
    public String test() {

        return this.restTemplate.getForObject("http://localhost:8080/testing", String.class);

    }

    @GetMapping("/test2")
    @CircuitBreaker(name = "test2service", fallbackMethod = "fallback")
    public String test2() {

        return this.restTemplate.getForObject("http://localhost:8081/testing2", String.class);

    }

    @GetMapping("/test3")
    @CircuitBreaker(name = "test3service", fallbackMethod = "fallback")
    public String test3() {

        return this.restTemplate.getForObject("http://localhost:8080/testing", String.class);

    }

    private String fallback(Throwable e) {

        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method";
    }

}
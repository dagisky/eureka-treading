package com.myRestful.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello Yordi");
    }

    @GetMapping(path = "/hello-world/{name}")
    public HelloWorldBean helloWorld(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello %s", name));
    }


}

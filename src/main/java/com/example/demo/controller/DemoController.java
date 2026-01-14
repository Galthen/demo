package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public void get() {
        System.out.println("Hello World");
    }

}
package com.seanrw.commerce.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HomeController {
    
    @GetMapping("/hello")
    public String home(Principal principal) {
        log.debug("Hello");
        return "Hello, " + principal.getName();
    }
}

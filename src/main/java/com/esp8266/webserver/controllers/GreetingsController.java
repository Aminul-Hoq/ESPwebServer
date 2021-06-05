package com.esp8266.webserver.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aminul Hoque
 * @since 2021-06-05
 */
@RestController
@RequestMapping("/greetings")
public class GreetingsController {

    @GetMapping
    public ResponseEntity greetings(){
        return ResponseEntity.ok("esp-web-server is running");
    }

}

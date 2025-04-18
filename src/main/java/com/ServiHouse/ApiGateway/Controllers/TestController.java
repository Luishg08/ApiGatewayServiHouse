package com.ServiHouse.ApiGateway.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    //Prueba Gateway
    @GetMapping("/test")
    public String test() {
        return "{\"message\": \"Hola desde ApiGateway\"}";
    }
}

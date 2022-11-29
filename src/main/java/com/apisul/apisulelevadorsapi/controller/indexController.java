package com.apisul.apisulelevadorsapi.controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class indexController {
    
    @GetMapping("/")
    public String principal() throws IOException {
        return  "Teste Grupo APISul";
    }
}

package org.example.securenotes.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/check")
    public String check() {
        return "check is working";
    }
}

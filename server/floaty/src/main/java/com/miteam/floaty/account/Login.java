package com.miteam.floaty.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class Login {
    @GetMapping(path = "/login")
    public Map<String, String> _hello() {
        Map<String, String> map = new HashMap<>();

        return map;
    }
}

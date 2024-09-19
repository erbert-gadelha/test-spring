package com.ebgr.controller;

import com.ebgr.repository.UserRepository;
import com.ebgr.service.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
public class Index {

    @Autowired
    UserRepository repository;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping("/")
    ResponseEntity<String> home(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if(userName.equals("anonymousUser"))
            userName = "foo";

        return ResponseEntity.ok(String.format(
                "<span>Hello <b>%s</b>!</span><div> %s %s %s %s </div>",
                    userName,
                    hiperText("usuários", "all"),
                    hiperText("sobre mim", "me"),
                    hiperText("registrar", "criar"),
                    hiperText("sair", "sair")));
    }




    static String hiperText(String text, String path) {
        return String.format("<a href=\"/%s\">%s</a>", path, text);
    }

}
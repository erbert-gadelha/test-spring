package com.ebgr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    final String loggedPage = "";

    @GetMapping("/todo")
    ResponseEntity<String> page() {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(userName.equals("anonymousUser"))
            return ResponseEntity.ok("você deve logar para acessar essa página.<br><a href=\"/\">home</a>");

        return ResponseEntity.ok(loggedPage);
    }
}

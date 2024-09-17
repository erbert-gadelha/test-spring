package com.ebgr.controller;

import com.ebgr.model.User;
import com.ebgr.repository.UserRepository;
import com.ebgr.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRepository repository;

    @GetMapping("/all")
    ResponseEntity<String> getAll() {
        List<User> users = repository.findAll();
        return ResponseEntity.ok(String.format(
                        "Usuários: {%s} <br> %s",
                        users.stream()
                                .map(User::toString)
                                .collect(Collectors.joining("<br>")),
                        hiperText("home", "")
                )
        );


    }

    @GetMapping("/me")
    ResponseEntity<String> aboutMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(
                authentication.getName() +
                        "<br>" + hiperText("home", "") +
                        "<form a></form>"
        );
    }



    @GetMapping("/sair")
    public String logout() {
        return "<span>Tela de Logout</span>";
    }

    @GetMapping("/criar")
    public String get_singin() {
        return "<form action=\"/api/criar\" method=\"post\"><input name=\"name\" type=text placeholder=\"name\"/> <br> <input name=\"password\" type=\"password\" placeholder=\"password\"/> <br> <input type=\"submit\" value=\"criar\"></form>";
    }

    @PostMapping("/api/criar")
    public ResponseEntity<String> post_singin(@ModelAttribute User user) {
        if(user.getName() == null | user.getPassword() == null | user.getName().isEmpty() | user.getPassword().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request params.");

        userDetailsService.createUser(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(String.format("User created successfully with id %d.", user.getId()));
    }


    static String hiperText(String text, String path) {
        return String.format("<a href=\"/%s\">%s</a>", path, text);
    }

}

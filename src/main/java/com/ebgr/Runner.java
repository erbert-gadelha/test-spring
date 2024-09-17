package com.ebgr;

import com.ebgr.model.User;
import com.ebgr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("user", "1234");
        User user2 = new User("usuario", "senha");
        System.out.println(user1);
        System.out.println(user2);

        user1.setPassword(encoder.encode(user1.getPassword()));
        user2.setPassword(encoder.encode(user2.getPassword()));

        repository.save(user1);
        repository.save(user2);
    }
}

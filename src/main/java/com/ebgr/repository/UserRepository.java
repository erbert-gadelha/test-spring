package com.ebgr.repository;

import com.ebgr.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    public Optional<User> findUserByName(String name);
    public List<User> findAll();
    //public Integer save(User user);
}
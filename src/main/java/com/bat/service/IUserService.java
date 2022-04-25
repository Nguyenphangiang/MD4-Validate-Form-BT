package com.bat.service;

import com.bat.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface IUserService {
    Iterable<User> showAll();

    Optional<User> findById(Long id);

    void save(User user);

    void remove(Long id);
}

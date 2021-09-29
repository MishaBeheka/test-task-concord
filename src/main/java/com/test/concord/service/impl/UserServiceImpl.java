package com.test.concord.service.impl;

import com.test.concord.exceptions.UserNotFountException;
import com.test.concord.model.User;
import com.test.concord.repository.UserRepository;
import com.test.concord.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFountException("Can't find user by id = " + id));
    }
}

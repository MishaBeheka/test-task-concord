package com.test.concord.service.impl;

import com.test.concord.dto.ResponseUserDto;
import com.test.concord.exceptions.UserNotFountException;
import com.test.concord.model.User;
import com.test.concord.repository.UserRepository;
import com.test.concord.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public ResponseUserDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFountException("Can't find user by id = " + id));
        log.info("Found user by id: {}", id);
        log.info("USER: {}", user.getFullName());
        return new ResponseUserDto(user.getFullName());
    }

    public void userInit() {
        Optional<User> optionalUser = userRepository.findById(1L);
        if (optionalUser.isPresent()) {
            log.info("User table not empty!");
        } else {
            User user = new User();
            user.setFullName("Test Testov");
            userRepository.save(user);
            log.info("Default user was initialized!");
        }
    }
}

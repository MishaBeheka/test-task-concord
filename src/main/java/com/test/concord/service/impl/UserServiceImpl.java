package com.test.concord.service.impl;

import com.test.concord.dto.ResponseUserDto;
import com.test.concord.exceptions.UserNotFountException;
import com.test.concord.model.User;
import com.test.concord.repository.UserRepository;
import com.test.concord.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        //TODO logs
        return new ResponseUserDto(user.getFullName());
    }

    public void userInit() {
        Optional<User> optionalUser = userRepository.findById(1L);
        if (optionalUser.isPresent()) {
            //TODO logs
        } else {
            User user = new User();
            user.setFullName("fsdfsd");
            user = userRepository.save(user);
            System.out.println(); //TODO logs
        }
    }
}

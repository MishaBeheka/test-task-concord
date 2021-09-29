package com.test.concord.service.impl;

import com.test.concord.dto.ResponseUserDto;
import com.test.concord.exceptions.UserNotFountException;
import com.test.concord.model.User;
import com.test.concord.repository.UserRepository;
import com.test.concord.service.EncryptionService;
import com.test.concord.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final EncryptionService encryptionService;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, EncryptionService encryptionService) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
    }


    @Override
    public ResponseUserDto findUserById(Long id) {
        String encryptRequest = encryptionService.encrypt(String.valueOf(id)).orElse("default value");
        log.info("=== encryption request: {}", encryptRequest);
        String decryptRequest = encryptionService.decrypt(encryptRequest).orElse("default value");
        log.info("=== decryption request: {}", decryptRequest);

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFountException("Can't find user by id = " + id));
        log.info("Found user by id: {}", id);

        ResponseUserDto responseUserDto = new ResponseUserDto(user.getFullName());
        String encryptResponse = encryptionService.encrypt(new JSONObject(responseUserDto).toString()).orElse("default value");
        log.info("=== encryption response: {}", encryptResponse);
        String decryptResponse = encryptionService.decrypt(encryptResponse).orElse("default value");
        log.info("=== decryption response: {}", decryptResponse);
        return responseUserDto;
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

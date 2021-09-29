package com.test.concord.service.impl;

import com.test.concord.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InitializeService {
    private final UserService userService;

    public InitializeService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void initialize() {
        userService.userInit();
    }
}

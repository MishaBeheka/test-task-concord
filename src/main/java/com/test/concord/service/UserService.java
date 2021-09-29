package com.test.concord.service;

import com.test.concord.dto.ResponseUserDto;

public interface UserService {

    ResponseUserDto findUserById(Long id);

    void userInit();
}

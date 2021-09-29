package com.test.concord.controller;


import com.test.concord.dto.RequestUserDto;
import com.test.concord.dto.ResponseUserDto;
import com.test.concord.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find-user-by-id")
    public ResponseEntity<ResponseUserDto> findUserBId(@RequestBody RequestUserDto userDto) {
        //TODO logs
        return ResponseEntity.ok(userService.findUserById(userDto.getId()));
    }
}

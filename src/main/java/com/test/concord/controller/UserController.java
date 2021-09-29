package com.test.concord.controller;


import com.test.concord.dto.RequestUserDto;
import com.test.concord.dto.ResponseUserDto;
import com.test.concord.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find-user-by-id")
    public ResponseEntity<?> findUserBId(@RequestBody RequestUserDto userDto) {
        log.info("Was calling API /find-user-by-id");
        log.info("Id = " + userDto.getId());
        if (userDto.getId() != 1) {
            return ResponseEntity.ok("NULL");
        }
        return ResponseEntity.ok(userService.findUserById(userDto.getId()));
    }
}

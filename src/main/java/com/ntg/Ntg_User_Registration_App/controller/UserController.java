package com.ntg.Ntg_User_Registration_App.controller;

import com.ntg.Ntg_User_Registration_App.dto.RegistrationRequestDto;
import com.ntg.Ntg_User_Registration_App.model.User;
import com.ntg.Ntg_User_Registration_App.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegistrationRequestDto userDto){
        User user = new User(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return new ResponseEntity<>( userService.register(user), HttpStatus.CREATED);
    }
    @GetMapping("/test-generic-error")
    public String testGenericError() {
        String str = null;
        // This will throw NullPointerException → caught by generic handler
        return str.toUpperCase();
    }

}

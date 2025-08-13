package com.ntg.Ntg_User_Registration_App.service;

import com.ntg.Ntg_User_Registration_App.model.User;
import com.ntg.Ntg_User_Registration_App.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public String register(User user){
        userRepository.save(user);
        return "New user has been added with id : "+user.getId();
    }
}

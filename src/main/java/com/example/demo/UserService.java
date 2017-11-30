package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Long countByEmail(String email){
        return userRepository.countByEmail(email);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("ROLE_USER")));
        user.setEnabled(true);
        System.out.println("userservice saved user");
        System.out.println("userservice " + user.toString());
        userRepository.save(user);
    }

    public void saveAdmin(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("ROLE_ADMIN")));
        user.setEnabled(true);
        userRepository.save(user);
    }
}

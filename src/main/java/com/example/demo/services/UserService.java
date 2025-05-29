package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.web.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public void createUser(UserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());

        this.repository.save(user);
    }

    public User getUserById(long id) {
        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with id does not exist"));
    }
}

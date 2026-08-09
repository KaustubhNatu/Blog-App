package com.blogapp.blogappapi.users;

import com.blogapp.blogappapi.users.DTOs.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(CreateUserRequest u) {
        var newUser = UserEntity.builder()
                .username(u.getUsername())
                //.password(password)  //TODO:Encrypt Password
                .email(u.getEmail())
                .build();

        return userRepository.save(newUser);

    }

    public UserEntity getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }

    public UserEntity getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
    }

    public UserEntity loginUser(String username, String password) {
        //TODO: check the password 
        return userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }

    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(String username) {
            super("User with name " + username + " not found.");
        }

        public UserNotFoundException(Long userId) {
            super("User with name " + userId + " not found.");
        }
    }
}

package com.blogapp.blogappapi.users;

import com.blogapp.blogappapi.users.dtos.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserEntity createUser(CreateUserRequest u) {

        if (userRepository.findByUsername(u.getUsername()).isPresent()) {
            return userRepository.findByUsername(u.getUsername()).get();
        }
        UserEntity newUser = modelMapper.map(u, UserEntity.class);
        //TODO:Encrypt Password

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

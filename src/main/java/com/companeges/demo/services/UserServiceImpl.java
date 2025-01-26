package com.companeges.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.companeges.demo.dto.UserPostPutRequestDTO;
import com.companeges.demo.models.User;
import com.companeges.demo.repositories.UserRepository;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserPostPutRequestDTO createUser(UserPostPutRequestDTO userPostPutRequestDTO) {
        User user = modelMapper.map(userPostPutRequestDTO, User.class);
        userRepository.save(user);
        return modelMapper.map(user, UserPostPutRequestDTO.class);
    }
    
}

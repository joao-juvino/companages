package com.companeges.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.companeges.demo.dto.UserPostPutRequestDTO;
import com.companeges.demo.models.User;

public class UserServiceImpl implements UserService {

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserPostPutRequestDTO createUser(UserPostPutRequestDTO userPostPutRequestDTO) {
        User user = modelMapper.map(userPostPutRequestDTO, User.class);
        animalsRepository.save(user);
        return modelMapper.map(user, UserPostPutRequestDTO.class);
    }
    
}

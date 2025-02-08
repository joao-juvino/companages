package com.companeges.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companeges.demo.dto.UserPostPutRequestDTO;
import com.companeges.demo.models.User;
import com.companeges.demo.repositories.UserRepository;

@Service
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

    @Override
    public List<UserPostPutRequestDTO> getUsers() {
        List<User> users = this.userRepository.findAll();

        return users.stream()
                .map(user -> new UserPostPutRequestDTO(user))
                .collect(Collectors.toList());
    }
    
}

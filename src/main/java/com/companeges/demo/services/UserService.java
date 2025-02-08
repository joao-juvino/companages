package com.companeges.demo.services;

import java.util.List;

import com.companeges.demo.dto.UserPostPutRequestDTO;

public interface UserService {
    UserPostPutRequestDTO createUser(UserPostPutRequestDTO userPostPutRequestDTO);
    List<UserPostPutRequestDTO> getUsers();
}

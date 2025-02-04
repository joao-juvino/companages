package com.companeges.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.companeges.demo.dto.PositionPostPutRequestDTO;
import com.companeges.demo.models.Position;
import com.companeges.demo.repositories.PositionRepository;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PositionRepository positionRepository;

    @Override
    public PositionPostPutRequestDTO createPosition(PositionPostPutRequestDTO positionPostPutRequestDTO) {
        Position position = modelMapper.map(positionPostPutRequestDTO, Position.class);
        positionRepository.save(position);
        return modelMapper.map(position, PositionPostPutRequestDTO.class);
    }
    
}

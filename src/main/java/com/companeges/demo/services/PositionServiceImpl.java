package com.companeges.demo.services;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<PositionPostPutRequestDTO> getPositions() {
        List<Position> positions = this.positionRepository.findAll();

        return positions.stream()
                .map(position -> new PositionPostPutRequestDTO(position))
                .collect(Collectors.toList());
    }
    
}

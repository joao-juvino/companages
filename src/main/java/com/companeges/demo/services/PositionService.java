package com.companeges.demo.services;

import java.util.List;

import com.companeges.demo.dto.PositionPostPutRequestDTO;

public interface PositionService {
    PositionPostPutRequestDTO createPosition(PositionPostPutRequestDTO positionPostPutRequestDTO);
    List<PositionPostPutRequestDTO> getPositions();
}

package com.estate.converter;

import com.estate.dto.CareDetailDTO;
import com.estate.entity.CareDetailEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CareDetailConverter {
    @Autowired
    private ModelMapper modelMapper ;

    public CareDetailDTO convertToDto(CareDetailEntity entity) {
        CareDetailDTO result = modelMapper.map(entity, CareDetailDTO.class);
        return result;
    }

    public CareDetailEntity convertToEntity(CareDetailDTO dto) {
        CareDetailEntity result = modelMapper.map(dto, CareDetailEntity.class);
        return result;
    }
}

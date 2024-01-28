package com.travel.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.travel.dto.SpotDto;
import com.travel.entity.Spot;

@Component
public class SpotConverter {

    // Converts SpotDto to Spot Entity
    public Spot convertDtoToEntity(SpotDto spotDto) {
        Spot spotEntity = new Spot();

        if (spotDto != null) {
            BeanUtils.copyProperties(spotDto, spotEntity);
            // You may need additional logic here for handling the package, if applicable
        }

        return spotEntity;
    }

    // Converts Spot Entity to SpotDto
    public SpotDto convertEntityToDto(Spot spotEntity) {
        SpotDto spotDto = new SpotDto();

        if (spotEntity != null) {
            BeanUtils.copyProperties(spotEntity, spotDto);
            // You may need additional logic here for handling the package, if applicable
        }

        return spotDto;
    }
}

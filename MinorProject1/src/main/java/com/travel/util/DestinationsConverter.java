package com.travel.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.travel.dto.DestinationsDto;
import com.travel.entity.Destinations;
@Component
//Indicates that this class is a Spring component and can be automatically scanned and managed by the Spring framework.

public class DestinationsConverter {
 
 // Converts DestinationsDto to Destination Entity
 public Destinations convertDtoToEntity(DestinationsDto destDto) {
     // Method to convert a DestinationsDto to a Destinations entity.

     Destinations dest = new Destinations();

     if (destDto != null) {
         BeanUtils.copyProperties(destDto, dest);
     }
     // If the provided DestinationsDto is not null, copy its properties to the Destinations entity.

     return dest;
 }

 // Converts Destination Entity to DestinationsDto
 public DestinationsDto convertEntityToDto(Destinations dest) {
     // Method to convert a Destinations entity to a DestinationsDto.

     DestinationsDto destDto = new DestinationsDto();

     if (dest != null) {
         BeanUtils.copyProperties(dest, destDto);
     }
     // If the provided Destinations entity is not null, copy its properties to the DestinationsDto.

     return destDto;
 }
}
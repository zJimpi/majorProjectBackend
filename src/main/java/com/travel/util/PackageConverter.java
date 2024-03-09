package com.travel.util;



import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.travel.dto.PackageDto;
import com.travel.entity.Package;
//import com.travel.entity.Spot;

@Component
//PackageConverter.java
public class PackageConverter {

// @Autowired
// private SpotConverter spotConverter;

 // Other methods...

 // Converts PackageDto to Package Entity
 public Package convertDtoToEntity(PackageDto packageDto) {
     Package packageEntity = new Package();

     if (packageDto != null) {
         BeanUtils.copyProperties(packageDto, packageEntity);
         // Convert spots from SpotDto to Spot entities if spots are present in the DTO
         
//         if (packageDto.getSpots() != null && !packageDto.getSpots().isEmpty()) {
//             List<Spot> spots = packageDto.getSpots().stream()
//                     .map(spotConverter::convertDtoToEntity)
//                     .collect(Collectors.toList());
//             packageEntity.setSpots(spots);
//         }
     }

     return packageEntity;
 }

 // Converts Package Entity to PackageDto
 public PackageDto convertEntityToDto(Package packageEntity) {
     PackageDto packageDto = new PackageDto();

     if (packageEntity != null) {
         BeanUtils.copyProperties(packageEntity, packageDto);
         // Convert spots from Spot entities to SpotDto if spots are present in the entity
//         if (packageEntity.getSpots() != null && !packageEntity.getSpots().isEmpty()) {
//             List<SpotDto> spots = packageEntity.getSpots().stream()
//                     .map(spotConverter::convertEntityToDto)
//                     .collect(Collectors.toList());
//             packageDto.setSpots(spots);
//         }
     }

     return packageDto;
 }
}

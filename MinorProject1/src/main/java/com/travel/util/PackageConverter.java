package com.travel.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.travel.dto.PackageDto;
import com.travel.entity.Package;

@Component
public class PackageConverter {

    // Converts PackageDto to Package Entity
    public Package convertDtoToEntity(PackageDto packageDto) {
        Package packageEntity = new Package();

        if (packageDto != null) {
            BeanUtils.copyProperties(packageDto, packageEntity);
        }

        return packageEntity;
    }

    // Converts Package Entity to PackageDto
    public PackageDto convertEntityToDto(Package packageEntity) {
        PackageDto packageDto = new PackageDto();

        if (packageEntity != null) {
            BeanUtils.copyProperties(packageEntity, packageDto);
        }

        return packageDto;
    }
}

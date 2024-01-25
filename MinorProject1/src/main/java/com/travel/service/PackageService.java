package com.travel.service;

import java.util.List;

import com.travel.dto.PackageDto;
import com.travel.entity.Package;

public interface PackageService {

    PackageDto savePackage(Package packageEntity);

    void deletePackageById(Long packageId);

    PackageDto updatePackage(Long packageId, Package packageEntity);

    List<PackageDto> getPackageList();

	PackageDto getPackageById(Long packageId);
}

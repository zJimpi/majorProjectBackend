package com.travel.service;

import java.util.List;

import com.travel.dto.HotelDto;
import com.travel.dto.PackageDto;
import com.travel.entity.Package;

public interface PackageService {


    void deletePackageById(Long packageId);

    PackageDto updatePackage(Long packageId, Package packageEntity);

    List<PackageDto> getPackageList();

	PackageDto getPackageById(Long packageId);

	PackageDto savePackage(Package packageEntity);
	
	String getSpotsByPackageId(Long packageId);
	
	List<HotelDto> getHotelListByPackageLocation(String packageLocation);
}

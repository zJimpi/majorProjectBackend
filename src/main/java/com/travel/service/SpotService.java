package com.travel.service;

import com.travel.dto.SpotDto;

import java.util.List;

import javax.validation.Valid;

public interface SpotService {

	SpotDto saveSpot(SpotDto spotDto);

	void deleteSpotById(Long spotId);

	SpotDto updateSpot(Long spotId, SpotDto spotDto);

	List<SpotDto> getSpotList();

	SpotDto getSpotById(Long spotId);

	List<SpotDto> updateSpotsForPackage(Long packageId, @Valid List<SpotDto> spots);


}

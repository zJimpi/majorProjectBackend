package com.travel.repository;

import com.travel.entity.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {

    @Query("SELECT s FROM Spot s WHERE s.travelPackage.pckgId = :packageId")
    List<Spot> findByPackageId(@Param("packageId") Long packageId);
}

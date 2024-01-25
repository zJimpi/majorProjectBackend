package com.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.entity.Package;

public interface PackageRepository extends JpaRepository<Package, Long> {

    @Query("SELECT p FROM Package p WHERE p.packageCode = :code")
    Package findByPackageCode(@Param("code") String code);

    Optional<Package> findByPackageCodeAndPckgIdNot(String packageCode, Long excludePackageId);

    // Other CRUD methods for the Package entity are inherited from JpaRepository.
}

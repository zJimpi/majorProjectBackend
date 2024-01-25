package com.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travel.entity.Destinations;

public interface DestinationsRepository extends JpaRepository<Destinations, Integer> {
    // This interface extends JpaRepository to perform common database operations on the Destinations entity.

    @Query("SELECT d FROM Destinations d WHERE d.destName = :name")
    // Specifies a custom query using JPQL (Java Persistence Query Language).
    Destinations findDestinationByName(@Param("name") String name);
    // This method finds a destination by its name using the custom query.

    // Other CRUD (Create, Read, Update, Delete) methods for the Destinations entity are inherited from JpaRepository.
}
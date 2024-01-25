package com.travel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Integer> {
    // This interface extends JpaRepository to perform common database operations on the FileData entity.

    Optional<FileData> findByName(String fileName);
    // This method finds a `FileData` entity by its name (fileName) and returns an Optional.
    // An Optional is used to indicate that the result may or may not exist, preventing potential null pointer exceptions.

    // Other CRUD (Create, Read, Update, Delete) methods for the FileData entity are inherited from JpaRepository.
}
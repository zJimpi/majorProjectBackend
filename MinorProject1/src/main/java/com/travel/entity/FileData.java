package com.travel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//Indicates that this class is an entity, which can be mapped to a database table.

@Table(name = "FILE_DATA")
//Specifies the name of the database table where this entity is stored.

@Data
//Lombok annotation that generates getter, setter, and other common methods for the class.

@AllArgsConstructor
//Lombok annotation that generates an all-args constructor for the entity.

@NoArgsConstructor
//Lombok annotation that generates a no-args constructor for the entity.

@Builder
//Lombok annotation that provides a builder pattern for creating instances of the class.

public class FileData {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 // Specifies that the 'id' field is the primary key and is automatically generated.
 private int id;
 // An integer field representing the unique identifier for a file data record.

 private String name;
 // A string field for the name of the file.

 private String type;
 // A string field for the type of the file.

 private String filePath;
 // A string field for the file's path or location.
}
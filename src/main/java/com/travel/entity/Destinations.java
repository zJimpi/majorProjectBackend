package com.travel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//Indicates that this class is an entity, which can be mapped to a database table.

@Table(name = "destinations_details")
//Specifies the name of the database table where this entity is stored.

@Getter
@Setter
//Lombok annotations that automatically generate getter and setter methods for the class fields.

@AllArgsConstructor
//Lombok annotation that generates an all-args constructor for the entity.

@NoArgsConstructor
//Lombok annotation that generates a no-args constructor for the entity.

@Inheritance(strategy = InheritanceType.JOINED)
//Specifies the inheritance strategy for this entity, using a joined table strategy.

public class Destinations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Specifies that the 'destId' field is the primary key and is automatically generated.
	private int destId;
	// An integer field representing the unique identifier for a destination.

	@Column(length = 50, nullable = false, unique = true)
	// Specifies the mapping of the 'destName' field to a database column.
	private String destName;
	// A string field for the name of a destination, with constraints for length, nullability, and uniqueness.

	@Column(length = 100, nullable = false, unique = true)
	// Specifies the mapping of the 'imageLocation' field to a database column.
	private String imageLocation;
	// A string field representing the location of an image, with constraints for length, nullability, and uniqueness.

	@Column(length = 150, nullable = true)
	// Specifies the mapping of the 'imageDescription' field to a database column.
	private String imageDescription;
	// A string field for a description of the image, with a length constraint, and it's not required.

	@Column(length = 50, nullable = false)
	// Specifies the mapping of the 'destType' field to a database column.
	private String destType;
	// A string field for the type of destination, with constraints for length and nullability.

	private float popularityScore;
	// A floating-point field representing the popularity score of the destination.

	@Column(length = 60, nullable = false)
	// Specifies the mapping of the 'stateAndUTName' field to a database column.
	private String stateAndUTName;
	// A string field for the state or union territory name, with constraints for length and nullability.

	private String imageFile;
	// A string field for the image file.
}
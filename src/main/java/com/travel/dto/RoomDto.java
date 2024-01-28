package com.travel.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {

    private Long roomId;

    @NotNull(message = "Hotel ID is required")
    private Long hotelId;

    @NotBlank(message = "Room Type is required")
    private String roomType;

    @NotBlank(message = "Room Name is required")
    private String roomName;

    @NotNull(message = "Price Per Day is required")
    private Double pricePerDay;
    
    // Additional fields if needed

    // Constructors, getters, setters
}

package com.travel.dto;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.travel.entity.Hotel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {

    private Long roomId;


    @NotBlank(message = "Room Type is required")
    private String roomType;

    @NotBlank(message = "Room Name is required")
    private String roomName;

    @NotNull(message = "Price Per Day is required")
    private Double pricePerDay;
    
    @ManyToOne
    private Hotel hotel;
    
    private String imageFile;
}

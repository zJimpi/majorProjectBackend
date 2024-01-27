package com.travel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class HotelDto {

    private int hotelId;

    @NotBlank(message = "Hotel name is required")
    @Size(max = 50, message = "Max. limit is 50")
    private String hotelName;

    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Max. limit is 100")
    private String hotelLocation;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Max. limit is 255")
    private String address;

    @Size(max = 20, message = "Max. limit is 20")
    private String hotelMobileNumber;

    @Size(max = 50, message = "Max. limit is 50")
    private String managerName;

    private List<RoomDto> room;
}

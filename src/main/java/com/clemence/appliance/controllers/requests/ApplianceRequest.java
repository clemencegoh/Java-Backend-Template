package com.clemence.appliance.controllers.requests;

import lombok.Data;

import java.util.Date;

@Data
public class ApplianceRequest {
    private String serialNumber;
    private String brand;
    private String model;
    private Date dateBought;
    private String status;
}

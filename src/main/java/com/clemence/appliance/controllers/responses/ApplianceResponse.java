package com.clemence.appliance.controllers.responses;

import com.clemence.appliance.models.Appliance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplianceResponse {
    private Long id;
    private String serialNumber;
    private String brand;
    private String model;
    private Date dateBought;
    private String status;

    public ApplianceResponse(Appliance appliance) {
        this.id = appliance.getId();
        this.serialNumber = appliance.getSerialNumber();
        this.brand = appliance.getBrand();
        this.model = appliance.getModel();
        this.dateBought = appliance.getDateBought();
        this.status = appliance.getStatus();
    }
}

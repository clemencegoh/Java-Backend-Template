package com.clemence.appliance.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Appliances")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appliance {
    @Id
    @GeneratedValue
    private Long id;
    private String serialNumber;
    private String brand;
    private String model;
    private Date dateBought;
    private String status;

}

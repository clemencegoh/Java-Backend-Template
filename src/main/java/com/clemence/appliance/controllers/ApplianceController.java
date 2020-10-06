package com.clemence.appliance.controllers;

import com.clemence.appliance.controllers.requests.ApplianceRequest;
import com.clemence.appliance.controllers.responses.ApplianceResponse;
import com.clemence.appliance.models.Appliance;
import com.clemence.appliance.services.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/appliances")
public class ApplianceController {

    @Autowired
    ApplianceService applianceService;

    @GetMapping
    public ResponseEntity<List<ApplianceResponse>> getAppliances(
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String dateBought,
            @RequestParam(required = false) String status,
            @RequestParam(required = false, defaultValue = "0") Long page,
            @RequestParam(required = false, defaultValue = "100") Long numberPerPage,
            @RequestParam(required = false, defaultValue = "") String sortBy
    ) {
        Date convertedDate = applianceService.convertStringToDate(dateBought);

        Appliance filter = new Appliance();
        filter.setSerialNumber(serialNumber);
        filter.setBrand(brand);
        filter.setModel(model);
        filter.setDateBought(convertedDate);
        filter.setStatus(status);

        List<Appliance> appliances = applianceService.getAppliancesFilterBy(filter, page, numberPerPage, sortBy);

        return ResponseEntity.ok(toApplianceResponse(appliances));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ApplianceResponse>> getAllAppliances() {
        List<Appliance> appliances = applianceService.getAllAppliances();
        return ResponseEntity.ok(toApplianceResponse(appliances));
    }

    @PostMapping
    public ResponseEntity<ApplianceResponse> createAppliance(@RequestBody ApplianceRequest request) {
        Appliance appliance = applianceService.createAppliance(toAppliance(request));
        return ResponseEntity.ok(toApplianceResponse(appliance));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplianceResponse> updateAppliance(
            @PathVariable Long id,
            @RequestBody ApplianceRequest request
    ) {
        Appliance appliance = applianceService.updateAppliance(toAppliance(request), id);
        return ResponseEntity.ok(toApplianceResponse(appliance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAppliance(@PathVariable Long id){
        try{
            applianceService.deleteAppliance(id);
            return ResponseEntity.ok(id);
        }catch (RuntimeException ex){
            return ResponseEntity.ok(-1L);
        }
    }

    private List<ApplianceResponse> toApplianceResponse(List<Appliance> request){
        return request.stream().map(ApplianceResponse::new).collect(Collectors.toList());
    }

    private Appliance toAppliance(ApplianceRequest request) {
        Appliance appliance = new Appliance();

        appliance.setSerialNumber(request.getSerialNumber());
        appliance.setBrand(request.getBrand());
        appliance.setModel(request.getModel());
        appliance.setDateBought(request.getDateBought());
        appliance.setStatus(request.getStatus());

        return appliance;
    }

    private ApplianceResponse toApplianceResponse(Appliance request) {
        ApplianceResponse appliance = new ApplianceResponse();

        appliance.setId(request.getId());
        appliance.setSerialNumber(request.getSerialNumber());
        appliance.setBrand(request.getBrand());
        appliance.setModel(request.getModel());
        appliance.setDateBought(request.getDateBought());
        appliance.setStatus(request.getStatus());

        return appliance;
    }
}

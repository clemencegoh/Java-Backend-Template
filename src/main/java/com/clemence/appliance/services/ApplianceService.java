package com.clemence.appliance.services;

import com.clemence.appliance.commons.queries.ApplianceSpecification;
import com.clemence.appliance.exceptions.ApplianceAlreadyExistsException;
import com.clemence.appliance.exceptions.ApplianceNotFoundException;
import com.clemence.appliance.exceptions.WrongDatePatternException;
import com.clemence.appliance.models.Appliance;
import com.clemence.appliance.repositories.ApplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ApplianceService {

    @Autowired
    ApplianceRepository applianceRepository;

    @Transactional
    public List<Appliance> getAllAppliances() {
        return applianceRepository.findAll();
    }

    @Transactional
    public List<Appliance> getAppliancesFilterBy(
            Appliance filter,
            Long page,
            Long numberPerPage,
            String sortedBy
    ) {
        Pageable pageable = PageRequest.of(page.intValue(), numberPerPage.intValue());


        Specification<Appliance> spec = new ApplianceSpecification(filter);

        Page<Appliance> currentPage = applianceRepository.findAll(spec, pageable);
        return currentPage.getContent();
    }

    @Transactional
    public Appliance createAppliance(Appliance appliance) {
        if (getApplianceByCompositeKey(
                appliance.getSerialNumber(),
                appliance.getBrand(),
                appliance.getModel()
        )) {
            throw new ApplianceAlreadyExistsException("Appliance already exists");
        }
        return applianceRepository.save(appliance);
    }

    @Transactional
    public Appliance updateAppliance(Appliance incomingAppliance, Long id) {
        if (applianceRepository.findById(id).isEmpty()) {
            throw new ApplianceNotFoundException("Appliance does not exist");
        }
        incomingAppliance.setId(id);
        return applianceRepository.save(incomingAppliance);
    }

    @Transactional
    public void deleteAppliance(Long id) {
        applianceRepository.deleteById(id);
    }

    // Helper functions
    private boolean getApplianceByCompositeKey(String serialNumber, String brand, String model) {
        // Custom logic for identifying unique using Serial Number, Brand, Model
        var appliances = applianceRepository.findAllByComposite(
                serialNumber, brand, model
        );
        return (appliances.size() != 0);
    }

    public Date convertStringToDate(String date) {
        if (date == null){
            return null;
        }
        try {
            return SimpleDateFormat.getDateInstance().parse(date);
        } catch (ParseException ex) {
            throw new WrongDatePatternException("Wrong date pattern");
        }
    }
}

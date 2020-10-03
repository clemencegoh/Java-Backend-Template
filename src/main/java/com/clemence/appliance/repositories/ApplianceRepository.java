package com.clemence.appliance.repositories;

import com.clemence.appliance.models.Appliance;
import com.sun.istack.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long>, JpaSpecificationExecutor<Appliance> {

    @Query(
            value = "SELECT * FROM Appliances where serial_number = ?1 and brand = ?2 and model = ?3",
            nativeQuery = true
    )
    public List<Appliance> findAllByComposite(String serialNumber, String brand, String model);


    @Query(
            value = "SELECT * FROM Appliances a " +
            "WHERE (a.serial_number = :serialNumber or :serialNumber is null) " +
            "and (a.brand = :brand OR :brand is null) " +
            "and (a.model = :model or :model is null) " +
            "and (a.date_bought = :dateBought or :dateBought is null) " +
            "and (a.status = :status or :status is null)",
            nativeQuery = true
    )
    public List<Appliance> findAllFilterBy(
            @Nullable @Param("serialNumber") String serialNumber,
            @Nullable @Param("brand") String brand,
            @Nullable @Param("model") String model,
            @Nullable @Param("dateBought") Date dateBought,
            @Nullable @Param("status") String status
    );
}

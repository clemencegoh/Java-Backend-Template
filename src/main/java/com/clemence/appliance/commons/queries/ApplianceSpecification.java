package com.clemence.appliance.commons.queries;

import com.clemence.appliance.models.Appliance;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ApplianceSpecification implements Specification<Appliance> {
    private final Appliance filter;

    public ApplianceSpecification(Appliance filter){
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Appliance> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        Predicate p = cb.disjunction();

        if (filter.getSerialNumber() == null || filter.getSerialNumber().isBlank()){
            filter.setSerialNumber("");
        }

        if (filter.getBrand() == null || filter.getBrand().isBlank()){
            filter.setBrand("");
        }

        if (filter.getModel() == null || filter.getModel().isBlank()){
            filter.setModel("");
        }

        if (filter.getStatus() == null || filter.getStatus().isBlank()){
            filter.setStatus("");
        }

        // todo: figure out how to include date in this
        p.getExpressions()
                .add(cb.and(
                        cb.like(root.get("serialNumber"),filter.getSerialNumber() + "%"),
                        cb.like(root.get("brand"),filter.getBrand() + "%"),
                        cb.like(root.get("model"),filter.getModel() + "%"),
                        cb.like(root.get("status"),filter.getStatus() + "%")
                        ));
        return p;
    }
}

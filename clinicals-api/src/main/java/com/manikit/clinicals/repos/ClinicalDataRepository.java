package com.manikit.clinicals.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manikit.clinicals.model.ClinicalData;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

}

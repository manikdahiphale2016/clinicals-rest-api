package com.manikit.clinicals.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manikit.clinicals.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
	

}

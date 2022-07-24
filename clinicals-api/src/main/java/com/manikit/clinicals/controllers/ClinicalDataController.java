package com.manikit.clinicals.controllers;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manikit.clinicals.dto.ClinicalDataRequest;
import com.manikit.clinicals.model.ClinicalData;
import com.manikit.clinicals.model.Patient;
import com.manikit.clinicals.repos.ClinicalDataRepository;
import com.manikit.clinicals.repos.PatientRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalDataController {
	
	private ClinicalDataRepository clinicalRepo;
	private PatientRepository patientRepo;
	
	@Autowired
	public ClinicalDataController(ClinicalDataRepository clinicalRepo,PatientRepository patientRepo) {
		this.clinicalRepo = clinicalRepo;
		this.patientRepo = patientRepo;
	}
	
	@PostMapping("/saveClinical")
	public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request) {
		Patient patient = patientRepo.findById(request.getPatientId()).get();
		ClinicalData clinicalData = new ClinicalData();
		clinicalData.setComponentName(request.getComponentName());
		clinicalData.setComponentValue(request.getComponentValue());
		clinicalData.setPatient(patient);
		
		clinicalData.setMeasuredDateTime(new Timestamp(new Date().getTime()));
		
		return clinicalRepo.save(clinicalData);
	}

}

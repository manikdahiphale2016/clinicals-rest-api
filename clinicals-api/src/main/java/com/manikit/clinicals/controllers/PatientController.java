package com.manikit.clinicals.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manikit.clinicals.model.ClinicalData;
import com.manikit.clinicals.model.Patient;
import com.manikit.clinicals.repos.PatientRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PatientController {

	private PatientRepository patientRepo;

	Map<String, String> filters = new HashMap<>();
	
	@Autowired
	public PatientController(PatientRepository patientRepo) {
		this.patientRepo = patientRepo;
	}

	@GetMapping(value = "/patients")
	public List<Patient> getPatients() {
		return patientRepo.findAll();

	}
	
	@GetMapping("/patients/{id}")
	public Patient getPatient(@PathVariable("id") int id) {
		return patientRepo.findById(id).get();
	}
	
	@PostMapping("/patient")
	public Patient savePatient(@RequestBody Patient patient) {
		return  patientRepo.save(patient);
	}
	
	@GetMapping("/patients/analyze/{id}")
	public Patient analyze(@PathVariable("id")  int id) {
		Patient patient = patientRepo.findById(id).get();
		List<ClinicalData> clinicalData = patient.getClinicalData();
		
		ArrayList<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
		for(ClinicalData eachEntry : duplicateClinicalData) {
			
			if(filters.containsKey(eachEntry.getComponentName())) {
				clinicalData.remove(eachEntry);
				continue;
			}else {
				filters.put(eachEntry.getComponentName(), null);
			}
			if(eachEntry.getComponentName().equals("hw")) {
			String[] heightAndWeight = eachEntry.getComponentValue().split("/");
			if(heightAndWeight != null && heightAndWeight.length > 1) {
			float heightInMeters =	Float.parseFloat(heightAndWeight[0]) * 0.4536F; 
			Float bmi = Float.parseFloat(heightAndWeight[1]) / (heightInMeters * heightInMeters);
			
			ClinicalData bmiData = new ClinicalData();
			bmiData.setComponentName("BMI");
			bmiData.setComponentValue(bmi.toString());
			clinicalData.add(bmiData);
				}
			}
		}
		filters.clear();
		return patient;	
	}

}

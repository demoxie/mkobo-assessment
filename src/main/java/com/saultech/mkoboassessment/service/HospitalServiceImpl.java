package com.saultech.mkoboassessment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.saultech.mkoboassessment.dto.response.APIResponse;
import com.saultech.mkoboassessment.entity.Patient;
import com.saultech.mkoboassessment.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalServiceImpl implements HospitalService{
    private final PatientRepository patientRepository;
    private final StaffService staffService;
    private final ObjectMapper mapper;
    @Override
    public APIResponse fetchAllPatientProfile(String staffUuid, Integer minimumAge) {
        if(!staffService.validateStaff(staffUuid)){
            return APIResponse.builder()
                    .status(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                    .message("Unauthorized")
                    .build();
        }

        List<Patient> result =  patientRepository.findAllByAgeGreaterThanEqual(minimumAge);
        return APIResponse.builder()
                .data(result)
                .status(String.valueOf(HttpStatus.OK.value()))
                .message("Success")
                .build();
    }

    @Override
    public APIResponse downloadPatientProfile(String staffUuid, String patientName) throws IOException {
        if(!staffService.validateStaff(staffUuid)){
            return APIResponse.builder()
                    .status(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                    .message("Unauthorized")
                    .build();
        }
        Optional<Patient> patient = patientRepository.findByName(patientName);
        if(patient.isPresent()){

            Patient patient1 = patient.get();
            File file = new File("src/main/resources/patients-profile/"+patient1.getName()+"_patient.csv");
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.registerModule(new JavaTimeModule());
            CsvSchema csvSchema = csvMapper.schemaFor(Patient.class).withHeader();
            csvMapper.writerFor(Patient.class)
                    .with(csvSchema)
                    .writeValue(file, patient1);


            return APIResponse.builder()
                    .data(patient.get())
                    .status(String.valueOf(HttpStatus.OK.value()))
                    .message("Success")
                    .build();
        }

        return APIResponse.builder()
                .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .message("Patient not found")
                .build();
    }

    @Override
    @Transactional
    public APIResponse deleteMultiplePatientProfile(String staffUuid, String ageRange) {
        if(!staffService.validateStaff(staffUuid)){
            return APIResponse.builder()
                    .status(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                    .message("Unauthorized")
                    .build();
        }
        String [] ageBoundary = ageRange.split("-");
        patientRepository.deleteAllByAgeBetween(Integer.parseInt(ageBoundary[0]), Integer.parseInt(ageBoundary[1]));
        return APIResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message("Success")
                .build();
    }
}

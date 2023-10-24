package com.saultech.mkoboassessment.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.saultech.mkoboassessment.dto.request.PaginationRequest;
import com.saultech.mkoboassessment.dto.response.APIResponse;
import com.saultech.mkoboassessment.entity.Patient;
import com.saultech.mkoboassessment.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
            //Convert to CSV

            Patient patient1 = patient.get();
            File file = new File(patient1.getName()+"_patient.csv");
            CsvMapper csvMapper = new CsvMapper();
            csvMapper.addMixIn(Patient.class, Patient.class);
            csvMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
            ObjectWriter objectWriter = csvMapper.writerFor(Patient.class);
            objectWriter.writeValue(file, patient1);

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
    public APIResponse deleteMultiplePatientProfile(String staffUuid, String ageRange) {
        if(!staffService.validateStaff(staffUuid)){
            return APIResponse.builder()
                    .status(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                    .message("Unauthorized")
                    .build();
        }
        patientRepository.deleteAllByAgeBetween(Integer.parseInt(ageRange.split("-")[0]), Integer.parseInt(ageRange.split("-")[1]));
        return APIResponse.builder()
                .status(String.valueOf(HttpStatus.OK.value()))
                .message("Success")
                .build();
    }
}

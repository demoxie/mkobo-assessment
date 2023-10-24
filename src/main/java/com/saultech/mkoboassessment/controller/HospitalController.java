package com.saultech.mkoboassessment.controller;

import com.saultech.mkoboassessment.dto.request.PaginationRequest;
import com.saultech.mkoboassessment.dto.response.APIResponse;
import com.saultech.mkoboassessment.service.HospitalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.saultech.mkoboassessment.constant.Routes.*;

@Tag(name = "Hospital", description = "Hospital API")
@RestController
@RequestMapping(HOSPITAL_BASE_ROUTE)
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllPatients(@RequestParam String staffUuid, @RequestParam Integer minimumAge){
        return ResponseEntity.ok(hospitalService.fetchAllPatientProfile(staffUuid, minimumAge));
    }

    @GetMapping("/download")
    public ResponseEntity<APIResponse> downloadPatientProfile(@RequestParam String staffUuid, @RequestParam String patientName) throws IOException {
        return ResponseEntity.ok(hospitalService.downloadPatientProfile(staffUuid, patientName));
    }

    @GetMapping("/delete")
    public ResponseEntity<APIResponse> deleteMultiplePatientProfile(@RequestParam String staffUuid, @RequestParam String ageRange){
        return ResponseEntity.ok(hospitalService.deleteMultiplePatientProfile(staffUuid, ageRange));
    }

}

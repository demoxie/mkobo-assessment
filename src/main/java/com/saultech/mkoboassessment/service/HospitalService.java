package com.saultech.mkoboassessment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.saultech.mkoboassessment.dto.response.APIResponse;
import com.saultech.mkoboassessment.dto.request.PaginationRequest;

import java.io.IOException;

public interface HospitalService {

    APIResponse fetchAllPatientProfile(String staffUuid, Integer minimumAge);

    APIResponse downloadPatientProfile(String staffUuid, String patientName) throws IOException;

    APIResponse deleteMultiplePatientProfile(String staffUuid, String ageRange);
}

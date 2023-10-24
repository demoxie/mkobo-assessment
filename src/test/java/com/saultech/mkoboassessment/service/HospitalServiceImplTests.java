package com.saultech.mkoboassessment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.saultech.mkoboassessment.dto.response.APIResponse;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class HospitalServiceImplTests {
    @Autowired
    private HospitalServiceImpl hospitalServiceImpl;

    /**
     * Method under test: {@link HospitalServiceImpl#fetchAllPatientProfile(String, Integer)}
     */
    @Test
    void testFetchAllPatientProfile() {
        APIResponse actualFetchAllPatientProfileResult = hospitalServiceImpl
                .fetchAllPatientProfile("01234567-89AB-CDEF-FEDC-BA9876543210", 1);
        assertNull(actualFetchAllPatientProfileResult.getData());
        assertEquals("401", actualFetchAllPatientProfileResult.getStatus());
        assertEquals("Unauthorized", actualFetchAllPatientProfileResult.getMessage());
    }

    /**
     * Method under test: {@link HospitalServiceImpl#downloadPatientProfile(String, String)}
     */
    @Test
    void testDownloadPatientProfile() throws IOException {
        APIResponse actualDownloadPatientProfileResult = hospitalServiceImpl
                .downloadPatientProfile("01234567-89AB-CDEF-FEDC-BA9876543210", "Patient Name");
        assertNull(actualDownloadPatientProfileResult.getData());
        assertEquals("401", actualDownloadPatientProfileResult.getStatus());
        assertEquals("Unauthorized", actualDownloadPatientProfileResult.getMessage());
    }

    /**
     * Method under test: {@link HospitalServiceImpl#deleteMultiplePatientProfile(String, String)}
     */
    @Test
    void testDeleteMultiplePatientProfile() {
        APIResponse actualDeleteMultiplePatientProfileResult = hospitalServiceImpl
                .deleteMultiplePatientProfile("01234567-89AB-CDEF-FEDC-BA9876543210", "Age Range");
        assertNull(actualDeleteMultiplePatientProfileResult.getData());
        assertEquals("401", actualDeleteMultiplePatientProfileResult.getStatus());
        assertEquals("Unauthorized", actualDeleteMultiplePatientProfileResult.getMessage());
    }
}


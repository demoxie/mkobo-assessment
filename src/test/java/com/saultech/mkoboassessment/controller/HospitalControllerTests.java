package com.saultech.mkoboassessment.controller;

import static org.mockito.Mockito.when;

import com.saultech.mkoboassessment.dto.response.APIResponse;
import com.saultech.mkoboassessment.service.HospitalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {HospitalController.class})
@ExtendWith(SpringExtension.class)
class HospitalControllerTests {
    @Autowired
    private HospitalController hospitalController;

    @MockBean
    private HospitalService hospitalService;

    /**
     * Method under test: {@link HospitalController#downloadPatientProfile(String, String)}
     */
    @Test
    void testDownloadPatientProfile() throws Exception {
        when(hospitalService.downloadPatientProfile(Mockito.<String>any(), Mockito.<String>any())).thenReturn(
                APIResponse.builder().data("Data").message("Not all who wander are lost").status("Status").build());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/hospital/download")
                .param("patientName", "foo")
                .param("staffUuid", "foo");
        MockMvcBuilders.standaloneSetup(hospitalController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"status\":\"Status\",\"message\":\"Not all who wander are lost\",\"data\":\"Data\"}"));
    }

    /**
     * Method under test: {@link HospitalController#deleteMultiplePatientProfile(String, String)}
     */
    @Test
    void testDeleteMultiplePatientProfile() throws Exception {
        when(hospitalService.deleteMultiplePatientProfile(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(APIResponse.builder().data("Data").message("Not all who wander are lost").status("Status").build());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/hospital/delete")
                .param("ageRange", "foo")
                .param("staffUuid", "foo");
        MockMvcBuilders.standaloneSetup(hospitalController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"status\":\"Status\",\"message\":\"Not all who wander are lost\",\"data\":\"Data\"}"));
    }

    /**
     * Method under test: {@link HospitalController#getAllPatients(String, Integer)}
     */
    @Test
    void testGetAllPatients() throws Exception {
        when(hospitalService.fetchAllPatientProfile(Mockito.<String>any(), Mockito.<Integer>any())).thenReturn(
                APIResponse.builder().data("Data").message("Not all who wander are lost").status("Status").build());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/hospital/all");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("minimumAge", String.valueOf(1))
                .param("staffUuid", "foo");
        MockMvcBuilders.standaloneSetup(hospitalController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"status\":\"Status\",\"message\":\"Not all who wander are lost\",\"data\":\"Data\"}"));
    }
}


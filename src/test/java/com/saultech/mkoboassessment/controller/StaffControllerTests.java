package com.saultech.mkoboassessment.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saultech.mkoboassessment.dto.request.CreateUserDto;
import com.saultech.mkoboassessment.dto.request.PaginationRequest;
import com.saultech.mkoboassessment.dto.response.APIResponse;
import com.saultech.mkoboassessment.service.StaffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StaffController.class})
@ExtendWith(SpringExtension.class)
class StaffControllerTests {
    @Autowired
    private StaffController staffController;

    @MockBean
    private StaffService staffService;

    /**
     * Method under test: {@link StaffController#createStaff(CreateUserDto)}
     */
    @Test
    void testCreateStaff() throws Exception {
        when(staffService.getAllStaff(Mockito.<PaginationRequest>any()))
                .thenReturn(APIResponse.builder().data("Data").message("Not all who wander are lost").status("Status").build());

        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setAge(1);
        createUserDto.setLastVisitDate(null);
        createUserDto.setName("Name");
        createUserDto.setRegistrationDate("2020-03-01");
        createUserDto.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        String content = (new ObjectMapper()).writeValueAsString(createUserDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/staffs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(staffController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"status\":\"Status\",\"message\":\"Not all who wander are lost\",\"data\":\"Data\"}"));
    }

    /**
     * Method under test: {@link StaffController#updateStaff(String, CreateUserDto)}
     */
    @Test
    void testUpdateStaff() throws Exception {
        when(staffService.updateStaff(Mockito.<String>any(), Mockito.<CreateUserDto>any())).thenReturn(
                APIResponse.builder().data("Data").message("Not all who wander are lost").status("Status").build());

        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setAge(1);
        createUserDto.setLastVisitDate(null);
        createUserDto.setName("Name");
        createUserDto.setRegistrationDate("2020-03-01");
        createUserDto.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        String content = (new ObjectMapper()).writeValueAsString(createUserDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/staffs/{uuid}", "01234567-89AB-CDEF-FEDC-BA9876543210")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(staffController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"status\":\"Status\",\"message\":\"Not all who wander are lost\",\"data\":\"Data\"}"));
    }

    /**
     * Method under test: {@link StaffController#deleteStaff(String)}
     */
    @Test
    void testDeleteStaff() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/staffs/{uuid}",
                "01234567-89AB-CDEF-FEDC-BA9876543210");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(staffController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }

    /**
     * Method under test: {@link StaffController#getAllStaff(PaginationRequest)}
     */
    @Test
    void testGetAllStaff() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/staffs");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(staffController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }

    /**
     * Method under test: {@link StaffController#getStaffById(String)}
     */
    @Test
    void testGetStaffById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/staffs/{uuid}",
                "01234567-89AB-CDEF-FEDC-BA9876543210");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(staffController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }
}


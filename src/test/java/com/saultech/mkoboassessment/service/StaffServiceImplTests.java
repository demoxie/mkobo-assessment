package com.saultech.mkoboassessment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.saultech.mkoboassessment.dto.request.CreateUserDto;
import com.saultech.mkoboassessment.dto.request.PaginationRequest;
import com.saultech.mkoboassessment.dto.response.APIResponse;
import com.saultech.mkoboassessment.entity.Staff;
import com.saultech.mkoboassessment.repository.StaffRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class StaffServiceImplTests {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private StaffRepository staffRepository;

    @Autowired
    private StaffServiceImpl staffServiceImpl;

    /**
     * Method under test: {@link StaffServiceImpl#createStaff(CreateUserDto)}
     */
    @Test
    void testCreateStaff() {
        Staff staff = new Staff();
        staff.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        staff.setId(1L);
        staff.setName("Name");
        staff.setRegistrationDate(LocalDate.of(1970, 1, 1));
        staff.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        staff.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(staffRepository.save(Mockito.<Staff>any())).thenReturn(staff);

        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setAge(1);
        createUserDto.setLastVisitDate(LocalDate.of(1970, 1, 1));
        createUserDto.setName("Name");
        createUserDto.setRegistrationDate("2020-03-01");
        createUserDto.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        APIResponse actualCreateStaffResult = staffServiceImpl.createStaff(createUserDto);
        assertSame(staff, actualCreateStaffResult.getData());
        assertEquals("201", actualCreateStaffResult.getStatus());
        assertEquals("Success", actualCreateStaffResult.getMessage());
        verify(staffRepository).save(Mockito.<Staff>any());
    }

    /**
     * Method under test: {@link StaffServiceImpl#getAllStaff(PaginationRequest)}
     */
    @Test
    void testGetAllStaff() {
        when(staffRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        APIResponse actualAllStaff = staffServiceImpl
                .getAllStaff(new PaginationRequest(10, 3, "Sort By", "Sort Direction"));
        assertTrue(((Collection<Object>) actualAllStaff.getData()).isEmpty());
        assertEquals("200", actualAllStaff.getStatus());
        assertEquals("Success", actualAllStaff.getMessage());
        verify(staffRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link StaffServiceImpl#getAllStaff(PaginationRequest)}
     */
    @Test
    void testGetAllStaff2() {
        when(staffRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        PaginationRequest paginationRequest = mock(PaginationRequest.class);
        when(paginationRequest.toPageable()).thenReturn(null);
        APIResponse actualAllStaff = staffServiceImpl.getAllStaff(paginationRequest);
        assertTrue(((Collection<Object>) actualAllStaff.getData()).isEmpty());
        assertEquals("200", actualAllStaff.getStatus());
        assertEquals("Success", actualAllStaff.getMessage());
        verify(staffRepository).findAll(Mockito.<Pageable>any());
        verify(paginationRequest).toPageable();
    }

    /**
     * Method under test: {@link StaffServiceImpl#getStaffById(String)}
     */
    @Test
    void testGetStaffById() {
        APIResponse actualStaffById = staffServiceImpl.getStaffById("01234567-89AB-CDEF-FEDC-BA9876543210");
        assertNull(actualStaffById.getData());
        assertEquals("404", actualStaffById.getStatus());
        assertEquals("Staff not found", actualStaffById.getMessage());
    }

    /**
     * Method under test: {@link StaffServiceImpl#updateStaff(String, CreateUserDto)}
     */
    @Test
    void testUpdateStaff() {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setAge(1);
        createUserDto.setLastVisitDate(LocalDate.of(1970, 1, 1));
        createUserDto.setName("Name");
        createUserDto.setRegistrationDate("2020-03-01");
        createUserDto.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        APIResponse actualUpdateStaffResult = staffServiceImpl.updateStaff("01234567-89AB-CDEF-FEDC-BA9876543210",
                createUserDto);
        assertNull(actualUpdateStaffResult.getData());
        assertEquals("401", actualUpdateStaffResult.getStatus());
        assertEquals("Unauthorized", actualUpdateStaffResult.getMessage());
    }

    /**
     * Method under test: {@link StaffServiceImpl#deleteStaff(String)}
     */
    @Test
    void testDeleteStaff() {
        APIResponse actualDeleteStaffResult = staffServiceImpl.deleteStaff("01234567-89AB-CDEF-FEDC-BA9876543210");
        assertNull(actualDeleteStaffResult.getData());
        assertEquals("404", actualDeleteStaffResult.getStatus());
        assertEquals("Staff not found", actualDeleteStaffResult.getMessage());
    }

    /**
     * Method under test: {@link StaffServiceImpl#validateStaff(String)}
     */
    @Test
    void testValidateStaff() {
        assertFalse(staffServiceImpl.validateStaff("01234567-89AB-CDEF-FEDC-BA9876543210"));
    }
}


package com.saultech.mkoboassessment.service;

import com.saultech.mkoboassessment.dto.request.CreateUserDto;
import com.saultech.mkoboassessment.dto.request.PaginationRequest;
import com.saultech.mkoboassessment.dto.response.APIResponse;


public interface StaffService {
    APIResponse createStaff(CreateUserDto createUserDto);

    APIResponse getAllStaff(PaginationRequest paginationRequest);

    APIResponse getStaffById(String uuid);

    APIResponse updateStaff(String uuid, CreateUserDto createUserDto);

    APIResponse deleteStaff(String uuid);

    boolean validateStaff(String uuid);

}

package com.saultech.mkoboassessment.service;

import com.saultech.mkoboassessment.dto.request.CreateUserDto;
import com.saultech.mkoboassessment.dto.request.PaginationRequest;
import com.saultech.mkoboassessment.dto.response.APIResponse;
import com.saultech.mkoboassessment.entity.Staff;
import com.saultech.mkoboassessment.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final ModelMapper mapper;
    @Override
    public APIResponse createStaff(CreateUserDto createUserDto) {
        Staff staff = new Staff();
        staff.setName(createUserDto.getName());
        staff.setCreatedAt(LocalDateTime.now());
        staff.setUuid(String.valueOf(UUID.randomUUID()));
        staff.setRegistrationDate(LocalDate.parse(createUserDto.getRegistrationDate()));
        log.info("Staff: {}", staff.toString());
        Staff savedStaff = staffRepository.save(staff);
        return APIResponse.builder()
                .data(savedStaff)
                .status(String.valueOf(HttpStatus.CREATED.value()))
                .message("Success")
                .build();
    }

    @Override
    public APIResponse getAllStaff(PaginationRequest paginationRequest) {
        Page<Staff> result =  staffRepository.findAll(paginationRequest.toPageable());
        return APIResponse.builder()
                .data(result.getContent())
                .status(String.valueOf(HttpStatus.OK.value()))
                .message("Success")
                .build();
    }

    @Override
    public APIResponse getStaffById(String uuid) {
        Optional<Staff> staff = staffRepository.findStaffByUuid(uuid);
        if(staff.isPresent()){
            return APIResponse.builder()
                    .data(staff.get())
                    .status(String.valueOf(HttpStatus.OK.value()))
                    .message("Success")
                    .build();
        }

        return APIResponse.builder()
                .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .message("Staff not found")
                .build();
    }

    @Override
    public APIResponse updateStaff(String uuid, CreateUserDto createUserDto) {
        if (!this.validateStaff(uuid)){
            return APIResponse.builder()
                    .status(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                    .message("Unauthorized")
                    .build();
        }
        Optional<Staff> optionalStaff = staffRepository.findStaffByUuid(uuid);
        if (optionalStaff.isPresent()){
            Staff staff = optionalStaff.get();
            staff.setName(createUserDto.getName());
            staff.setRegistrationDate(LocalDate.parse(createUserDto.getRegistrationDate()));
            staff.setUpdatedAt(LocalDateTime.now());
            Staff savedStaff = staffRepository.save(staff);
            return APIResponse.builder()
                    .data(savedStaff)
                    .status(String.valueOf(HttpStatus.OK.value()))
                    .message("Success")
                    .build();
        }
        return APIResponse.builder()
                .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .message("Staff not found")
                .build();
    }

    @Override
    public APIResponse deleteStaff(String uuid) {
        Optional<Staff> optionalStaff = staffRepository.findStaffByUuid(uuid);
        if (optionalStaff.isPresent()){
            staffRepository.delete(optionalStaff.get());
            return APIResponse.builder()
                    .status(String.valueOf(HttpStatus.OK.value()))
                    .message("Success")
                    .build();
        }
        return APIResponse.builder()
                .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .message("Staff not found")
                .build();
    }

    @Override
    public boolean validateStaff(String uuid) {
        Optional<Staff> optionalStaff = staffRepository.findStaffByUuid(uuid);
        return optionalStaff.isPresent();
    }

}

package com.saultech.mkoboassessment.controller;

import com.saultech.mkoboassessment.dto.request.CreateUserDto;
import com.saultech.mkoboassessment.dto.request.PaginationRequest;
import com.saultech.mkoboassessment.dto.response.APIResponse;
import com.saultech.mkoboassessment.service.StaffService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.saultech.mkoboassessment.constant.Routes.STAFF_BASE_ROUTE;


@Tag(name = "Staff", description = "Staff API")
@RestController
@RequestMapping(value = STAFF_BASE_ROUTE, consumes = "application/json", produces = "application/json")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<APIResponse> createStaff(@Valid @RequestBody CreateUserDto createUserDto){
        return ResponseEntity.ok(staffService.createStaff(createUserDto));
    }

    @GetMapping
    public ResponseEntity<APIResponse> getAllStaff(PaginationRequest paginationRequest){
        return ResponseEntity.ok(staffService.getAllStaff(paginationRequest));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<APIResponse> getStaffById(@PathVariable String uuid){
        return ResponseEntity.ok(staffService.getStaffById(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<APIResponse> updateStaff(@PathVariable String uuid, @Valid @RequestBody CreateUserDto createUserDto){
        return ResponseEntity.ok(staffService.updateStaff(uuid, createUserDto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<APIResponse> deleteStaff(@PathVariable String uuid){
        return ResponseEntity.ok(staffService.deleteStaff(uuid));
    }
}

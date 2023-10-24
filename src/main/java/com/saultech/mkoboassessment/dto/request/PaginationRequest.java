package com.saultech.mkoboassessment.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequest {
    private Integer pageNumber;
    private Integer pageSize;
    private String sortBy;
    private String sortDirection;

    public Pageable toPageable() {
        return PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));
    }

}

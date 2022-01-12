package com.alianz.test.controller;

import com.alianz.test.Exception.ElementNotFoundException;
import com.alianz.test.Exception.JsonFileException;
import com.alianz.test.dto.CompanyDto;
import com.alianz.test.dto.request.CompanyRequest;
import com.alianz.test.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alianz-test-api/v1/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDto> addCompany(@Valid @RequestBody CompanyRequest request) throws JsonFileException {
        CompanyDto companyDto = companyService.addCompany(request);
        return ResponseEntity.ok(companyDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyDto>> getCompanyDtoList() throws JsonFileException {
        List<CompanyDto> companyDtoList = companyService.getCompanyList();
        return ResponseEntity.ok(companyDtoList);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDto> getCompanyDto(@PathVariable("id") Long id) throws JsonFileException, ElementNotFoundException {
        CompanyDto companyDto = companyService.getCompany(id);
        return ResponseEntity.ok(companyDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDto> updateCompanyDto(@Valid @RequestBody CompanyRequest request) throws JsonFileException, ElementNotFoundException {
        CompanyDto companyDto = companyService.updateCompany(request);
        return ResponseEntity.ok(companyDto);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCompanyDto(@PathVariable("id") Long id) throws JsonFileException, ElementNotFoundException {
        companyService.deleteCompany(id);
        return ResponseEntity.ok().build();
    }
}

package com.allianz.maventest.rest.restcontroller;

import com.allianz.maventest.model.Company;
import com.allianz.maventest.webservice.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyRestController {

    private final CompanyService companyService;

    CompanyRestController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAll() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> saveCompany(@RequestBody Company newCompany) {
        return new ResponseEntity<>(companyService.saveCompany(newCompany), HttpStatus.CREATED);
    }

}

package com.alianz.test.service;

import com.alianz.test.Exception.ElementNotFoundException;
import com.alianz.test.Exception.JsonFileException;
import com.alianz.test.dto.CompanyDto;
import com.alianz.test.dto.request.CompanyRequest;

import java.util.List;

public interface CompanyService {

    CompanyDto addCompany(CompanyRequest companyRequest) throws JsonFileException;
    CompanyDto getCompany(Long id) throws JsonFileException, ElementNotFoundException;
    List<CompanyDto> getCompanyList() throws JsonFileException;
    CompanyDto updateCompany(CompanyRequest companyRequest) throws JsonFileException, ElementNotFoundException;
    void deleteCompany(Long id) throws JsonFileException, ElementNotFoundException;
}

package com.allianz.maventest.webservice.service;

import com.allianz.maventest.model.Company;
import com.allianz.maventest.webservice.provider.CompanyProvider;

import java.util.List;

public class CompanyService {

    private final CompanyProvider companyProvider;

    public CompanyService(CompanyProvider companyProvider) {
        this.companyProvider = companyProvider;
    }

    public List<Company> getAllCompanies() {
        return companyProvider.getCompanies();
    }

    public Company saveCompany(Company newCompany) {
        companyProvider.getCompanies().add(newCompany);
        return newCompany;
    }

}

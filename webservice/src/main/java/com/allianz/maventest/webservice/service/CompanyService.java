package com.allianz.maventest.webservice.service;

import com.allianz.maventest.model.Company;
import com.allianz.maventest.webservice.provider.CompanyProvider;
import com.allianz.maventest.webservice.request.UpdateCompanyRequest;

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

    public Company updateCompany(int companyId, UpdateCompanyRequest request) {
        return companyProvider.getCompanies()
                .stream()
                .filter(company -> company.getId() == companyId)
                .findFirst()
                .map(companyToUpdate -> updateCompany(companyToUpdate, request))
                .orElseThrow(() -> new RuntimeException("Cannot update company with id: " + companyId));
    }

    public void deleteCompany(int companyId) {
        var companies = companyProvider.getCompanies();
        var optionalCompany = companies
                .stream()
                .filter(company -> company.getId() == companyId)
                .findFirst();
        if (optionalCompany.isPresent()) {
            deleteCompany(companies, optionalCompany.get());
        } else {
            throw new RuntimeException("Company with id:" + companyId + " is not found");
        }
    }

    private Company updateCompany(Company companyToUpdate, UpdateCompanyRequest request) {
        companyToUpdate.setLabel(request.getLabel());
        companyToUpdate.setDate(request.getDate());
        return companyToUpdate;
    }

    private void deleteCompany(List<Company> companies, Company companyToDelete) {
        companies.remove(companyToDelete);
    }

}

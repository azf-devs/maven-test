package com.allianz.maventest.webservice.service;


import com.allianz.maventest.model.Company;
import com.allianz.maventest.webservice.provider.CompanyProvider;
import com.allianz.maventest.webservice.request.UpdateCompanyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    private CompanyService companyService;

    @Mock
    private CompanyProvider companyProviderMock;

    @BeforeEach
    void setup() {
        this.companyService = new CompanyService(companyProviderMock);
    }

    @Test
    void should_get_companies_ok() {
        // GIVEN
        var existingCompanies = Arrays.asList(
                new Company(1, "allianz", LocalDate.of(2022, 10, 24)),
                new Company(2, "toto", LocalDate.of(2022, 7, 9))
        );

        // WHEN
        when(companyProviderMock.getCompanies()).thenReturn(existingCompanies);
        var result = companyService.getAllCompanies();

        // THEN
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(existingCompanies, result);
    }

    @Test
    void should_add_company_ok() {
        // GIVEN
        var existingCompanies = new ArrayList<Company>();
        existingCompanies.add(new Company(1, "allianz", LocalDate.of(2022, 10, 24)));
        var newCompany = new Company(2, "new company", LocalDate.of(2022, 5, 1));

        // WHEN
        when(companyProviderMock.getCompanies()).thenReturn(existingCompanies);
        var result = companyService.saveCompany(newCompany);

        // THEN
        assertNotNull(result);
        assertEquals(2, existingCompanies.size());
        assertEquals(result, existingCompanies.get(1));
    }

    @Test
    void should_update_company_ok() {
        // GIVEN
        var companyId = 2;
        var updateCompanyRequest = new UpdateCompanyRequest("titi", LocalDate.now());
        var existingCompanies = new ArrayList<Company>();
        existingCompanies.add(new Company(1, "allianz", LocalDate.of(2022, 10, 24)));
        existingCompanies.add(new Company(2, "toto", LocalDate.of(2022, 5, 6)));

        // WHEN
        when(companyProviderMock.getCompanies()).thenReturn(existingCompanies);
        var result = companyService.updateCompany(companyId, updateCompanyRequest);

        // THEN
        assertNotNull(result);
        assertEquals(2, existingCompanies.size());
        assertEquals(result, existingCompanies.get(1));
    }

    @Test
    void should_delete_company_ko_when_company_not_exist() {
        // GIVEN
        var companyId = 2;
        var existingCompanies = new ArrayList<Company>();
        existingCompanies.add(new Company(1, "allianz", LocalDate.of(2022, 10, 24)));

        // WHEN
        when(companyProviderMock.getCompanies()).thenReturn(existingCompanies);
        var exception =
                assertThrows(
                        RuntimeException.class, () -> companyService.deleteCompany(companyId));

        // THEN
        assertEquals(
                "Company with id:" + companyId + " is not found", exception.getMessage());
    }

    @Test
    void should_delete_company_ok() {
        // GIVEN
        var companyId = 1;
        var existingCompanies = new ArrayList<Company>();
        existingCompanies.add(new Company(1, "allianz", LocalDate.of(2022, 10, 24)));

        // WHEN
        when(companyProviderMock.getCompanies()).thenReturn(existingCompanies);
        companyService.deleteCompany(companyId);

        // THEN
        assertEquals(emptyList(), existingCompanies);
    }

}

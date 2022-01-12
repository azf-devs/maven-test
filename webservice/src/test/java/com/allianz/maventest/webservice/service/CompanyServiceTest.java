package com.allianz.maventest.webservice.service;


import com.allianz.maventest.model.Company;
import com.allianz.maventest.webservice.provider.CompanyProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    private static final List<Company> COMPANIES = List.of(
            new Company(1, "allianz", LocalDate.of(2022, 10, 24)),
            new Company(2, "toto", LocalDate.of(2022, 7, 9))
    );

    private CompanyService companyService;

    @Mock
    private CompanyProvider companyProviderMock;

    @BeforeEach
    void setup() {
        this.companyService = new CompanyService(companyProviderMock);
        when(companyProviderMock.getCompanies()).thenReturn(COMPANIES);
    }

    @Test
    void should_get_companies_ok() {
        var companies = companyService.getAllCompanies();
        assertNotNull(companies);
        assertEquals(2, companies.size());
        assertEquals(COMPANIES, companies);
    }

}

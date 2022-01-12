package com.allianz.maventest.rest.provider;

import com.allianz.maventest.model.Company;
import com.allianz.maventest.webservice.provider.CompanyProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component("companyProvider")
public class DefaultCompanyProvider implements CompanyProvider {

    private static final String COMPANIES_JSON_PATH = "rest/src/main/resources/json/companies.json";

    private List<Company> companies = new ArrayList<>();
    private final ObjectMapper objectMapper;

    DefaultCompanyProvider(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        initCompanies();
    }

    @Override
    public List<Company> getCompanies() {
        return companies;
    }

    private void initCompanies() {
        try {
            var path = Paths.get(COMPANIES_JSON_PATH).toAbsolutePath();
            companies = objectMapper.readValue(new File(path.toString()), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("An error has occurred in read file: " + e.getMessage());
        }
    }
}

package com.alianz.test.util;

import com.alianz.test.Exception.JsonFileException;
import com.alianz.test.dto.CompanyDto;
import com.alianz.test.enums.AlianzTestError;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class CompanyFileUtils {

    private final String COMPANY_FILE_PATH = "/files/company.json";

    public List<CompanyDto> getCompanyDtoListFromFile() throws JsonFileException {
        try {
            List<CompanyDto> companyDtoList = new ObjectMapper().readValue(CompanyDto.class
                    .getResourceAsStream(COMPANY_FILE_PATH), new TypeReference<>() {});
            return companyDtoList;
        }
        catch (Exception e) {
            throw new JsonFileException(AlianzTestError.JSON_READ_FILE_EXCEPTION, e);
        }
    }


    public void saveCompanyListToFile(List<CompanyDto> companyDtoList) throws JsonFileException {
        try {
            File file = new ClassPathResource(COMPANY_FILE_PATH).getFile();
            new ObjectMapper().writeValue(file, companyDtoList);
        } catch (IOException e) {
            throw new JsonFileException(AlianzTestError.JSON_SAVE_FILE_EXCEPTION, e);
        }

    }

}

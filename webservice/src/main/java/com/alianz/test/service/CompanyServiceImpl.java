package com.alianz.test.service;

import com.alianz.test.Exception.ElementNotFoundException;
import com.alianz.test.Exception.JsonFileException;
import com.alianz.test.dto.CompanyDto;
import com.alianz.test.dto.request.CompanyRequest;
import com.alianz.test.enums.AlianzTestError;
import com.alianz.test.util.CompanyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {


    @Autowired
    private CompanyFileUtils companyFileUtils;

    @Override
    public CompanyDto addCompany(CompanyRequest companyRequest) throws JsonFileException {
        CompanyDto companyDto = new CompanyDto();

        List<CompanyDto> companyDtoList = getCompanyList();

        Long companyId = generateNextID(companyDtoList);

        companyDto.setId(companyId);
        companyDto.setLabel(companyRequest.getLabel());
        companyDto.setDate(companyRequest.getDate());

        companyDtoList.add(companyDto);

        companyFileUtils.saveCompanyListToFile(companyDtoList);
        return companyDto;
    }

    @Override
    public CompanyDto getCompany(Long id) throws JsonFileException, ElementNotFoundException {
        List<CompanyDto> companyDtoList = getCompanyList();
        return companyDtoList.stream().filter(c -> c.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ElementNotFoundException(AlianzTestError.ELEMENT_NOT_FOUND_EXCEPTION.code,
                        MessageFormat.format(AlianzTestError.ELEMENT_NOT_FOUND_EXCEPTION.message, id)));
    }

    @Override
    public List<CompanyDto> getCompanyList() throws JsonFileException {
        return companyFileUtils.getCompanyDtoListFromFile();
    }

    @Override
    public CompanyDto updateCompany(CompanyRequest companyRequest) throws JsonFileException, ElementNotFoundException {

        List<CompanyDto> companyDtoList = getCompanyList();

        CompanyDto companyDto = companyDtoList.stream().filter(c -> c.getId().equals(companyRequest.getId())).map(c -> {
                    c.setLabel(companyRequest.getLabel());
                    c.setDate(companyRequest.getDate());
                    return c;
                })
                .findFirst().orElseThrow(() ->
                        new ElementNotFoundException(AlianzTestError.ELEMENT_NOT_FOUND_EXCEPTION.code,
                                MessageFormat.format(AlianzTestError.ELEMENT_NOT_FOUND_EXCEPTION.message, companyRequest.getId())));

        companyFileUtils.saveCompanyListToFile(companyDtoList);
        return companyDto;
    }

    @Override
    public void deleteCompany(Long id) throws JsonFileException, ElementNotFoundException {
        List<CompanyDto> companyDtoList = getCompanyList();
        Boolean removed = companyDtoList.removeIf(companyDto -> companyDto.getId().equals(id));
        if(!removed){
            throw new ElementNotFoundException(AlianzTestError.ELEMENT_NOT_FOUND_EXCEPTION.code,
                    MessageFormat.format(AlianzTestError.ELEMENT_NOT_FOUND_EXCEPTION.message, id));
        }
        companyFileUtils.saveCompanyListToFile(companyDtoList);
    }


    private long generateNextID(List<CompanyDto> companyDtoList) {
        if(companyDtoList.size() == 0 ){
            return 0;
        }
        return companyDtoList.stream().mapToLong(c -> c.getId()).max().getAsLong() + 1;
    }

}

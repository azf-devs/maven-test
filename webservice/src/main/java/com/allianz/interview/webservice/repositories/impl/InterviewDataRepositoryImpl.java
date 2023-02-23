package com.allianz.interview.webservice.repositories.impl;

import com.allianz.interview.dto.InterviewData;
import com.allianz.interview.webservice.exception.DataNotFoundException;
import com.allianz.interview.webservice.repositories.InterviewDataRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class InterviewDataRepositoryImpl implements InterviewDataRepository {
    private Map<Integer, InterviewData> interviewDataMap = new HashMap<>();

    public InterviewDataRepositoryImpl() throws IOException, URISyntaxException {
     this.init();
    }

    @Override
    public InterviewData persist(InterviewData interviewData) {
        interviewData.setId(this.interviewDataMap.values().size() + 1);
        interviewData.setDate(LocalDate.now());
        interviewDataMap.put(interviewData.getId(), interviewData);
        return interviewDataMap.get(interviewData.getId());
    }

    @Override
    public boolean remove(Integer id) {
        InterviewData interviewDataToDelete = this.get(id);
        return interviewDataMap.remove(id, interviewDataToDelete);
    }

    @Override
    public InterviewData update(InterviewData interviewData) {
        InterviewData interviewDataToUpdate = this.get(interviewData.getId());

        interviewDataMap.put(interviewData.getId(), interviewData);

        return  interviewDataMap.get(interviewData.getId());
    }

    @Override
    public InterviewData get(Integer id) {
        if (!interviewDataMap.containsKey(id)) {
            throw new DataNotFoundException();
        }

        return interviewDataMap.get(id);
    }

    @Override
    public List<InterviewData> getAll() {
        return new ArrayList<>(this.interviewDataMap.values());
    }

    public void init() throws URISyntaxException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ClassLoader classLoader = getClass().getClassLoader();
        List<InterviewData> interviewData = objectMapper.readValue(classLoader.getResourceAsStream("interviewData.json"), new TypeReference<List<InterviewData>>() {
        });

        this.interviewDataMap = interviewData.stream()
                .collect(Collectors.toMap(InterviewData::getId, Function.identity()));
    }
}

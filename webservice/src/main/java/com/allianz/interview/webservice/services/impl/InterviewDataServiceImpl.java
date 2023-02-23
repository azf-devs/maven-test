package com.allianz.interview.webservice.services.impl;


import com.allianz.interview.dto.InterviewData;
import com.allianz.interview.webservice.repositories.InterviewDataRepository;
import com.allianz.interview.webservice.services.InterviewDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class InterviewDataServiceImpl implements InterviewDataService {

    @Autowired
    private InterviewDataRepository interviewDataRepository;


    @Override
    public InterviewData add(InterviewData interviewData) {
        log.info("ajout d'un nouvel item", interviewData);
        return interviewDataRepository.persist(interviewData);
    }

    @Override
    public boolean delete(Integer interviewDataId) {
        log.info("suppression d'un item", interviewDataId);
        return interviewDataRepository.remove(interviewDataId);
    }

    @Override
    public InterviewData update(Integer id, InterviewData interviewData) {
        log.info("mise à jour d'un item", id , interviewData);
        interviewData.setId(id);
        return interviewDataRepository.update(interviewData);
    }

    @Override
    public InterviewData get(Integer interviewDataId) {
        log.info("lecture d'un item", interviewDataId);
        return interviewDataRepository.get(interviewDataId);
    }

    @Override
    public List<InterviewData> getAll() {
        return interviewDataRepository.getAll();
    }
}

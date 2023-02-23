package com.allianz.interview.webservice.repositories;

import com.allianz.interview.dto.InterviewData;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface InterviewDataRepository {

    public InterviewData persist(InterviewData interviewData);
    public boolean remove(Integer id);
    public InterviewData update(InterviewData interviewData);
    public InterviewData get(Integer id);

    public List<InterviewData> getAll();


    public void init() throws URISyntaxException, IOException;
}

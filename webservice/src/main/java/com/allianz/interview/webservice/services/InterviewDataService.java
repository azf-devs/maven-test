package com.allianz.interview.webservice.services;


import com.allianz.interview.dto.InterviewData;

import java.util.List;


public interface InterviewDataService {

    public InterviewData add(InterviewData interviewData);

    public boolean delete(Integer interviewDataId);

    public InterviewData update(Integer interviewDataId, InterviewData interviewData);

    public InterviewData get(Integer interviewDataId);


   public List<InterviewData> getAll();
}

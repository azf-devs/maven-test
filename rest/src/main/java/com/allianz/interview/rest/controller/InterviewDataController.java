package com.allianz.interview.rest.controller;

import com.allianz.interview.dto.InterviewData;
import com.allianz.interview.webservice.services.InterviewDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("interviewdata")
@RestController
@Slf4j
public class InterviewDataController {

    @Autowired
    private InterviewDataService interviewDataService;


    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)

    public List<InterviewData> getAll() {
        return interviewDataService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    public InterviewData get(@PathVariable("id") Integer id) {
        return interviewDataService.get(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public InterviewData add(@RequestBody InterviewData interviewData) {
        return interviewDataService.add(interviewData);
    }


    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public InterviewData update(@PathVariable Integer id, @RequestBody InterviewData interviewData) {
        return interviewDataService.update(id, interviewData);
    }


    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@PathVariable Integer id) {
        return interviewDataService.delete(id);
    }


}

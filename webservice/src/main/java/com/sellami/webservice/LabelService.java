package com.sellami.webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sellami.model.Label;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public interface LabelService {
    Label create(Label label,HttpSession session) throws JsonProcessingException;

    List<Label> read(HttpSession session) throws IOException;

    Label update(Long id, Label label, HttpSession session) throws JsonProcessingException;

    void delete(Long id,HttpSession session) throws JsonProcessingException;
}

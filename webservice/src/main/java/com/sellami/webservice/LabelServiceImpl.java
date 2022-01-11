package com.sellami.webservice;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sellami.model.Label;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


@Service
public class LabelServiceImpl implements LabelService{

    @Value("classpath:labels.json")
    Resource resourceFile;

    public Label create(Label label,HttpSession session) throws JsonProcessingException {
        checkIfFileEmpty(session);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Label> labelList = new ArrayList<>(Arrays.asList(objectMapper.readValue(session.getAttribute("file").toString(), Label[].class)));
        if(label.getId()==null){
            labelList.sort(Comparator.comparing(Label::getId));
            Long lastId = labelList.get(labelList.size()-1).getId();
            lastId++;
            label.setId(lastId);
        }
        labelList.add(label);
        session.setAttribute("file", objectMapper.writeValueAsString(labelList));
        return label;
    }

    public List<Label> read(HttpSession session) throws IOException {
        checkIfFileEmpty(session);
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(session.getAttribute("file").toString(), Label[].class));
    }

    public Label update(Long id, Label label, HttpSession session) throws JsonProcessingException {
        checkIfFileEmpty(session);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Label> labelList = Arrays.asList(objectMapper.readValue(session.getAttribute("file").toString(), Label[].class));
        if(labelList.stream().anyMatch(l->l.getId().equals(id))){
            labelList.stream().filter(l->l.getId().equals(id)).findFirst().get().setLabel(label.getLabel());
            labelList.stream().filter(l->l.getId().equals(id)).findFirst().get().setDate(label.getDate());
            session.setAttribute("file", objectMapper.writeValueAsString(labelList));
            return labelList.stream().filter(l->l.getId().equals(id)).findFirst().get();
        }else return null;

    }

    public void delete(Long id,HttpSession session) throws JsonProcessingException {
        checkIfFileEmpty(session);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Label> labelList =  new ArrayList<>(Arrays.asList(objectMapper.readValue(session.getAttribute("file").toString(),
                Label[].class)));
        labelList.removeIf(l->l.getId().equals(id));
        session.setAttribute("file", objectMapper.writeValueAsString(labelList));
    }


    private void checkIfFileEmpty(HttpSession session){
        if(session.getAttribute("file")==null){
            try {
                session.setAttribute("file",fileToString(resourceFile.getFile().getPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String fileToString(String path) throws IOException {
        return FileUtils.readFileToString(ResourceUtils.getFile(path), StandardCharsets.UTF_8);
    }

}

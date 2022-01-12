package com.sellami.webservice;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sellami.model.FileItem;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Service
public class FileItemServiceImpl implements FileItemService {

    @Value("classpath:labels.json")
    Resource resourceFile;

    @Override
    public FileItem create(FileItem label, HttpSession session) throws JsonProcessingException {
        checkIfFileEmpty(session);
        ObjectMapper objectMapper = new ObjectMapper();
        List<FileItem> labelList = new ArrayList<>(Arrays.asList(objectMapper.readValue(session.getAttribute("file").toString(), FileItem[].class)));
        if(label.getId()==null){
            labelList.sort(Comparator.comparing(FileItem::getId));
            Long lastId = labelList.get(labelList.size()-1).getId();
            lastId++;
            label.setId(lastId);
        }
        labelList.add(label);
        session.setAttribute("file", objectMapper.writeValueAsString(labelList));
        return label;
    }
    @Override
    public List<FileItem> read(HttpSession session) throws IOException {
        checkIfFileEmpty(session);
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(session.getAttribute("file").toString(), FileItem[].class));
    }

    @Override
    public FileItem read(HttpSession session,Long id) throws IOException {
        checkIfFileEmpty(session);
        ObjectMapper objectMapper = new ObjectMapper();
        List<FileItem> labelList = Arrays.asList(objectMapper.readValue(session.getAttribute("file").toString(), FileItem[].class));
        return labelList.stream().filter(l->l.getId().equals(id)).findFirst().orElse(null);

    }
    @Override
    public FileItem update(Long id, FileItem label, HttpSession session) throws JsonProcessingException {
        checkIfFileEmpty(session);
        ObjectMapper objectMapper = new ObjectMapper();
        List<FileItem> labelList = Arrays.asList(objectMapper.readValue(session.getAttribute("file").toString(), FileItem[].class));
        labelList.stream().filter(l->l.getId().equals(id)).findFirst().ifPresent(l->l.setLabel(label.getLabel()));
        labelList.stream().filter(l->l.getId().equals(id)).findFirst().ifPresent(l->l.setDate(label.getDate()));
        session.setAttribute("file", objectMapper.writeValueAsString(labelList));
        return labelList.stream().filter(l->l.getId().equals(id)).findFirst().orElse(null);
    }
    @Override
    public void delete(Long id,HttpSession session) throws JsonProcessingException {
        checkIfFileEmpty(session);
        ObjectMapper objectMapper = new ObjectMapper();
        List<FileItem> labelList =  new ArrayList<>(Arrays.asList(objectMapper.readValue(session.getAttribute("file").toString(),
                FileItem[].class)));
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

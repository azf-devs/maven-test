package com.sellami.webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sellami.model.FileItem;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public interface FileItemService {
    FileItem create(FileItem label, HttpSession session) throws JsonProcessingException;
    List<FileItem> read(HttpSession session) throws IOException;
    FileItem read(HttpSession session,Long id) throws IOException;
    FileItem update(Long id, FileItem label, HttpSession session) throws JsonProcessingException;
    void delete(Long id,HttpSession session) throws JsonProcessingException;
}

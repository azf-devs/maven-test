package com.sellami.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sellami.model.FileItem;
import com.sellami.webservice.FileItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping(value="")
public class FileItemController {

    FileItemService fileItemService;

    public FileItemController(FileItemService fileItemService) {
        this.fileItemService = fileItemService;
    }

    @PostMapping("/items")
    public ResponseEntity<FileItem> create(@RequestBody FileItem Label, HttpSession session)
            throws  JsonProcessingException {
        FileItem createdLabel = fileItemService.create(Label,session);
        if (createdLabel == null) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(createdLabel, HttpStatus.CREATED);
        }
    }


    @GetMapping("/items/list")
    public ResponseEntity<List<FileItem>> read(HttpSession session) throws IOException {
        List<FileItem> foundLabel = fileItemService.read(session);
        if (foundLabel == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundLabel);
        }
    }
    @GetMapping("/items/{id}")
    public ResponseEntity<FileItem> read(HttpSession session ,@PathVariable Long id) throws IOException {
        FileItem foundLabel = fileItemService.read(session,id);
        if (foundLabel == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundLabel);
        }
    }


    @PutMapping("/items/{id}")
    public ResponseEntity<FileItem> update(HttpSession session, @RequestBody FileItem FileItem, @PathVariable Long id) throws JsonProcessingException {
        FileItem updatedLabel = fileItemService.update(id, FileItem,session);
        if (updatedLabel == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedLabel);
        }
    }


    @DeleteMapping("/items/{id}")
    public ResponseEntity<Object> deleteLabel(@PathVariable Long id,HttpSession session) throws JsonProcessingException {
        fileItemService.delete(id,session);
        return ResponseEntity.noContent().build();
    }
}

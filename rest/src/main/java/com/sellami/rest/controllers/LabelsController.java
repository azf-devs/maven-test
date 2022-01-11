package com.sellami.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sellami.model.Label;
import com.sellami.webservice.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController()
@RequestMapping(value="/labels")
public class LabelsController {

    @Autowired
    LabelService labelService;

    @PostMapping("/")
    public ResponseEntity<Label> create(@RequestBody Label Label,HttpSession session)
            throws  JsonProcessingException {
        Label createdLabel = labelService.create(Label,session);
        if (createdLabel == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdLabel.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdLabel);
        }
    }


    @GetMapping("/list")
    public ResponseEntity<List<Label>> read(HttpSession session) throws IOException {
        List<Label> foundLabel = labelService.read(session);
        if (foundLabel == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundLabel);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Label> update(HttpSession session,@RequestBody Label Label, @PathVariable Long id) throws JsonProcessingException {
        Label updatedLabel = labelService.update(id, Label,session);
        if (updatedLabel == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLabel(@PathVariable Long id,HttpSession session) throws JsonProcessingException {
        labelService.delete(id,session);
        return ResponseEntity.noContent().build();
    }
}

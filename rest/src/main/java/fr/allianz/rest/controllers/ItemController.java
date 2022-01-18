package fr.allianz.rest.controllers;

import entities.Item;
import fr.allianz.webservice.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        List<Item> items = itemService.findAll();
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        Item newItem = itemService.create(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }
}

package application.controller;

import domain.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.ItemService;

import java.io.IOException;
import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) throws IOException {
        return new ResponseEntity<>(
                itemService.getItemById(id),
                HttpStatus.OK);
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItem() throws IOException {
        return new ResponseEntity<>(
                itemService.getAllItems(),
                HttpStatus.OK);
    }

    @PostMapping("/items")
    public ResponseEntity<Item>  addItem(@RequestBody Item item) {
        return new ResponseEntity<>(
                itemService.addItem(item),
                HttpStatus.OK);
    }

    @PutMapping("/items")
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        return new ResponseEntity<>(
                itemService.updateItem(item),
                HttpStatus.OK);
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}

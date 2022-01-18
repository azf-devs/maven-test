package fr.allianz.webservice.services;

import fr.allianz.model.entities.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();
    Item create(Item item);
    Item update(int id, Item item);
    void delete(int id);
    Item findByIdOrFail(int id);
}

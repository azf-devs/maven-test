package service;

import domain.Item;

import java.io.IOException;
import java.util.List;

public interface ItemService {
     Item getItemById(Long id) throws IOException;
     List<Item> getAllItems() throws IOException;
     Item addItem(Item item);
     Item updateItem(Item item);
     void deleteItem(Long id);
}

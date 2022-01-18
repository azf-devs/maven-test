package repositories;

import entities.Item;
import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    List<Item> findAll();
    Item save(Item item);
    Optional<Item> findById(int id);
    void delete(int id);
}

package fr.allianz.model.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.allianz.model.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemFileRepository implements ItemRepository {

    private static int lastId = 0;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Item> items;

    @Value("${allianz.model.items-file}")
    private String itemsFile;

    @PostConstruct
    private void init() {
        try {
            items = objectMapper.readValue(Item.class
                    .getResourceAsStream(itemsFile), new TypeReference<>() {});
            items.sort(Comparator.comparing(Item::getId));
            lastId = items.get(items.size() - 1).getId();
        } catch (IOException e) {
            items = Collections.emptyList();
        }
    }

    @Override
    public List<Item> findAll() {
        return items;
    }

    @Override
    public Item save(Item item) {
        List<Item> items = findAll();
        for (Item itemIt : items) {
            if (itemIt.getId() == item.getId()) {
                itemIt = item;
                return itemIt;
            }
        }

        item.setId(++lastId);
        items.add(item);
        return item;
    }

    @Override
    public Optional<Item> findById(int id) {
        return findAll().stream().filter(item -> item.getId() == id).findFirst();
    }

    @Override
    public void delete(int id) {
        findAll().removeIf(item -> item.getId() == id);
    }
}

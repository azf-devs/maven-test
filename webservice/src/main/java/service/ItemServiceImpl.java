package service;

import domain.Item;
import mapper.ItemMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    List<Item> items = new ArrayList<>();
    private ItemMapper itemMapper = new ItemMapper();

    public ItemServiceImpl(){
        try {
            items = getAllItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Item getItemById(Long id) throws IOException {
        if (items.isEmpty()) {
            Optional<Item> itemToFind = itemMapper.readJsonWithItemMapper().stream()
                    .filter(item -> item.getId() == id)
                    .findFirst();
            return Optional.ofNullable(itemToFind)
                    .get().orElse(null);
        }
        Optional<Item> itemToFind = items.stream().filter(i -> i.getId().equals(id))
                .findFirst();
        return Optional.ofNullable(itemToFind)
                .get().orElse(null);
    }

    @Override
    public List<Item> getAllItems() throws IOException {
        if (items.isEmpty()) {
            items = itemMapper.readJsonWithItemMapper();
        }
        return items;
    }

    @Override
    public Item addItem(Item item) {
        items.add(item);
        return item;
    }

    @Override
    public Item updateItem(Item item) {
        Optional<Item> itemToUpdate = items.stream().filter(i -> i.getId() == item.getId())
                .findFirst();
        if(itemToUpdate.isPresent()) {
            itemToUpdate.get().setLabel(item.getLabel());
            itemToUpdate.get().setDate(item.getDate());
        }
        return item;
    }

    @Override
    public void deleteItem(Long id) {
        Optional<Item> itemToDelete = items.stream().filter(i -> i.getId().equals(id))
                .findFirst();
        if(itemToDelete.isPresent()) {
            items.remove(itemToDelete.get());
        }
    }

}

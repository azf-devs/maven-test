package services;

import entities.Item;
import exceptions.EntityNotFoundException;
import exceptions.InvalidEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.ItemRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ItemCrudServiceTest {

    ItemService itemService;

    ItemRepository itemRepository;

    @BeforeEach
    public void setUp() {
        itemRepository = mock(ItemRepository.class);
        itemService = new ItemServiceImpl(itemRepository);
    }

    @Test
    public void findAll_ReturnItemsFromRepository() {
        List<Item> stubItems = List.of(new Item(1, "Allianz"));

        when(itemRepository.findAll()).thenReturn(stubItems);

        List<Item> items = itemService.findAll();

        assertEquals(stubItems, items);
    }

    @Test
    public void create_ReturnsNewItemFromRepositoryWithId() {
        LocalDate date = LocalDate.of(2020, 1, 1);
        String label = "New Allianz";

        Item itemFromRepo = new Item(1, label, date);
        when(itemRepository.save(any())).thenReturn(itemFromRepo);

        Item newItem = itemService.create(new Item(label, date));

        assertEquals(itemFromRepo, newItem);
    }

    @Test
    public void create_GivenInvalidItem_ThrowsException() {
        assertThrows(InvalidEntityException.class, () -> itemService.create(new Item("", null)));
    }

    @Test
    public void update_UpdatesItemFromRepository() {
        LocalDate oldDate = LocalDate.of(2019, 10, 1);
        LocalDate newDate = LocalDate.of(   2020, 1, 15);
        String newLabel = "Allianz-updated";
        int id = 1;
        Item newItem = new Item(newLabel, newDate);
        newItem.setId(id);

        when(itemRepository.findById(id)).thenReturn(Optional.of(new Item(id, "Allianz", oldDate)));
        when(itemRepository.save(new Item(1, newLabel, newDate)))
                .thenReturn(new Item(1, newLabel, newDate));

        Item item = itemService.update(1, new Item(newLabel, newDate));

        assertEquals(new Item(1, newLabel, newDate), item);
    }

    @Test
    public void update_GivenInvalidItem_ThrowsException() {
        assertThrows(InvalidEntityException.class, () -> itemService.update(1, new Item("", null)));
    }

    @Test
    public void update_GivenNonExisting_ThrowsException() {
        when(itemRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> itemService.update(1, new Item("foo", LocalDate.of(2020, 1, 1))));
    }
}

package fr.allianz.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import entities.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import fr.allianz.webservice.services.ItemService;
import org.springframework.test.web.servlet.RequestBuilder;

import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemService itemService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void findAll_ShouldReturnAllItems() throws Exception {

        when(itemService.findAll()).thenReturn(Arrays.asList(
                new Item(1, "allianz", LocalDate.of(2020, 10, 1)),
                new Item(2, "toto", LocalDate.of(2020, 10, 2)),
                new Item(3, "oto", LocalDate.of(2020, 10, 1))
        ));

        String expectedJson = "[{'id': 1, 'label': 'allianz', 'date': '2020-10-01'}," +
                "{'id': 2, 'label': 'toto', 'date': '2020-10-02'}," +
                "{'id': 3, 'label': 'oto', 'date': '2020-10-01'}]";

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void create_ShouldCallCreateOnItemServiceAndReturnItem() throws Exception {

        Item itemToCreate = new Item("Foo-Allianz", LocalDate.of(2020, 1, 1));

        when(itemService.create(itemToCreate))
                .thenReturn(new Item(1, "Foo-Allianz", LocalDate.of(2020, 1, 1)));

        RequestBuilder requestBuilder = post("/items")
                .content(itemToJson(itemToCreate))
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'id': 1, 'label': 'Foo-Allianz', 'date':  '2020-01-01'}"));
    }

    private String itemToJson(Item item) throws JsonProcessingException {
        ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(item);
    }

    @Test
    public void update_ShouldCallUpdateOnItemServiceAndReturnUpdatedItem() throws Exception {
        String newLabel = "Foo-Updated";
        LocalDate newDate = LocalDate.of(2020, 2, 2);
        Item itemToUpdate = new Item(newLabel, newDate);

        when(itemService.update(1, itemToUpdate))
                .thenReturn(new Item(1, newLabel, newDate));

        RequestBuilder requestBuilder = put("/items/1")
                .content(itemToJson(itemToUpdate))
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'id': 1, 'label': 'Foo-Updated', 'date':  '2020-02-02'}"));
    }

    @Test
    public void delete_ShouldCallDeleteOnItemService() throws Exception {
        mockMvc.perform(delete("/items/1"))
                .andExpect(status().isNoContent());
        verify(itemService, times(1)).delete(1);
    }
}

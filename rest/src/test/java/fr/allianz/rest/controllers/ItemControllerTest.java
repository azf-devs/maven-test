package fr.allianz.rest.controllers;

import entities.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import fr.allianz.webservice.services.ItemService;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemService itemService;

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

        MvcResult result = mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson))
                .andReturn();

        System.out.println(result);
    }
}

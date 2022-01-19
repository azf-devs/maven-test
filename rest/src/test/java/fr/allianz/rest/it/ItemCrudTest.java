package fr.allianz.rest.it;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemCrudTest {

    @Autowired
    MockMvc mockMvc;

    private void performFindAllAndExpectJson(String expectedJson) throws Exception {
        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    private void performFindByIdAndExpectJson(int id, String expectedJson) throws Exception {
        mockMvc.perform(get("/items/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    @Order(1)
    public void findAll() throws Exception {
        String expectedJson = "[{'id': 1, 'label': 'allianz', 'date': '2021-10-01'}," +
                "{'id': 2, 'label': 'toto', 'date': '2021-10-02'}," +
                "{'id': 3, 'label': 'oto', 'date': '2021-10-01'}]";

        performFindAllAndExpectJson(expectedJson);
    }

    @Test
    @Order(2)
    public void findById() throws Exception {
        String expectedJson = "{'id': 3, 'label': 'oto', 'date': '2021-10-01'}";
        performFindByIdAndExpectJson(3, expectedJson);
    }


    @Test
    @Order(3)
    public void create() throws Exception {
        String expectedCreateJson = "{'id': 4, 'label': 'Foo', 'date': '2021-10-10'}";

        RequestBuilder requestBuilder = post("/items")
                .content("{\"label\": \"Foo\", \"date\": \"2021-10-10\"}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedCreateJson));

        String expectedFindAllJson = "[{'id': 1, 'label': 'allianz', 'date': '2021-10-01'}," +
                "{'id': 2, 'label': 'toto', 'date': '2021-10-02'}," +
                "{'id': 3, 'label': 'oto', 'date': '2021-10-01'}," +
                expectedCreateJson + "]";
        performFindAllAndExpectJson(expectedFindAllJson);
    }

    @Test
    @Order(4)
    public void update() throws Exception {
        String expectedUpdateJson = "{'id': 3, 'label': 'New oto', 'date': '2021-12-12'}";

        RequestBuilder requestBuilder = put("/items/3")
                .content("{\"label\": \"New oto\", \"date\": \"2021-12-12\"}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedUpdateJson));

        String expectedFindAllJson = "[{'id': 1, 'label': 'allianz', 'date': '2021-10-01'}," +
                "{'id': 2, 'label': 'toto', 'date': '2021-10-02'}," +
                expectedUpdateJson + "," +
                "{'id': 4, 'label': 'Foo', 'date': '2021-10-10'}]";
        performFindAllAndExpectJson(expectedFindAllJson);
    }

    @Test
    @Order(5)
    public void deleteExistingItem() throws Exception {
        mockMvc.perform(delete("/items/4"))
                .andExpect(status().isNoContent());

        String expectedFindAllJson = "[{'id': 1, 'label': 'allianz', 'date': '2021-10-01'}," +
                "{'id': 2, 'label': 'toto', 'date': '2021-10-02'}," +
                "{'id': 3, 'label': 'New oto', 'date': '2021-12-12'}]";
        performFindAllAndExpectJson(expectedFindAllJson);
    }

    @Test
    @Order(6)
    public void updateInvalidItem() throws Exception {
        RequestBuilder requestBuilder = put("/items/3")
                .content("{\"label\": \"\", \"date\": \"2021-12-12\"}")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(7)
    public void findById_InvalidItem() throws Exception {
        mockMvc.perform(get("/items/10000"))
                .andExpect(status().isNotFound());
    }
}

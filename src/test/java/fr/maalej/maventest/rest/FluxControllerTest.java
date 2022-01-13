package fr.maalej.maventest.rest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import fr.maalej.maventest.exceptions.IllegalArgumentException;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FluxControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void should_show_entries() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"id\":1,\"label\":\"allianz\",\"date\":\"2021-10-01\"},{\"id\":2,\"label\":\"toto\",\"date\":\"2021-10-02\"},{\"id\":3,\"label\":\"oto\",\"date\":\"2021-10-01\"},{\"id\":4,\"label\":\"123456654321\",\"date\":\"2021-10-03\"},{\"id\":5,\"label\":\"kayak\",\"date\":\"2021-10-04\"},{\"id\":6,\"label\":\"radar\",\"date\":\"2021-10-05\"},{\"id\":7,\"label\":\"sagas\",\"date\":\"2021-10-02\"}]")));
    }

    @Test
    @Order(2)
    public void should_return_entry_byId() throws Exception {
        this.mockMvc.perform(get("/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"label\":\"allianz\",\"date\":\"2021-10-01\"}")));

        this.mockMvc.perform(get("/25")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @Order(3)
    public void should_delete_entry_byId() throws Exception {
        this.mockMvc.perform(delete("/1")).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    @Order(4)
    public void should_create_new_entry() throws Exception {
        this.mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content("{\"id\":10,\"label\":\"allianz\",\"date\":\"2021-10-01\"}")).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    @Order(5)
    public void should_update_new_entry() throws Exception {
        this.mockMvc.perform(patch("/").contentType(MediaType.APPLICATION_JSON).content("{\"id\":3,\"label\":\"allianz\",\"date\":\"2021-10-01\"}")).andDo(print()).andExpect(status().isNoContent());
    }
}

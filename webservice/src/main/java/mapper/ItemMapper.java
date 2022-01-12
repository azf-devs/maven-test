package mapper;

import domain.Item;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemMapper {

    public List<Item> readJsonWithItemMapper() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("./webservice/src/main/resources/item.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Item.class));
    }
}

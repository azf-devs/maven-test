package fr.maalej.maventest.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.maalej.maventest.model.Entry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EntryResourceReader {

    private static String DATE_FORMAT = "yyyy-MM-dd";
    @Value("${flux.datasource}")
    private Resource resourceFile;

    private ObjectMapper objectMapper;

    @PostConstruct
    public void setup() {
        objectMapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        objectMapper.setDateFormat(dateFormat);
    }

    public List<Entry> read() {
        try {
            return Arrays.asList(objectMapper.readValue(resourceFile.getFile(), Entry[].class));
        } catch (IOException e) {
            return new ArrayList<Entry>();
        }
    }
}

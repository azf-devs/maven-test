package fr.maalej.maventest.webservice;

import fr.maalej.maventest.model.Entry;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DataTest {
    public static List<Entry> mockedEntries = Arrays.asList(
            new Entry(1, "allianz", new Date(2021, 10, 1)),
            new Entry(2, "toto", new Date(2021, 10, 2)),
            new Entry(3, "oto", new Date(2021, 10, 1)),
            new Entry(4, "123456654321", new Date(2021, 10, 3)),
            new Entry(5, "kayak", new Date(2021, 10, 4)),
            new Entry(6, "radar", new Date(2021, 10, 5)),
            new Entry(7, "sagas", new Date(2021, 10, 2))
    );
}

package fr.maalej.maventest.webservice;

import fr.maalej.maventest.exceptions.IllegalArgumentException;
import fr.maalej.maventest.exceptions.NoSuchElementFoundException;
import fr.maalej.maventest.model.Entry;
import fr.maalej.maventest.util.EntryResourceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FluxService {

    @Autowired
    private EntryResourceReader entrySource;

    private List<Entry> entries = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.entries.addAll(entrySource.read());
    }

    public List<Entry> findAll() {
        return this.entries;
    }

    private Entry findById(Integer id) {
        Optional<Entry> result = this.entries.stream().filter(entry -> entry.getId() == id).findFirst();

        if (!result.isPresent()) {
            return null;
        }

        return result.get();
    }

    public Entry find(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }

        Entry result = findById(id);

        if (result == null) {
            throw new NoSuchElementFoundException();
        }

        return result;
    }

    public void update(Entry updatedEntry) {
        if (updatedEntry == null || findById(updatedEntry.getId()) == null) {
            throw new NoSuchElementFoundException();
        }

        this.entries = this.entries.stream().filter(entry -> entry.getId() != updatedEntry.getId()).collect(Collectors.toList());
        this.entries.add(updatedEntry);
    }

    public void create(Entry entry) {
        if (entry == null || findById(entry.getId()) != null) {
            throw new IllegalArgumentException();
        }

        this.entries.add(entry);
    }

    public void delete(Integer id) {
        if (id == null || findById(id) == null) {
            throw new NoSuchElementFoundException();
        }

        this.entries = this.entries.stream().filter(entry -> entry.getId() != id).collect(Collectors.toList());
    }
}

package fr.maalej.maventest.rest;

import fr.maalej.maventest.exceptions.IllegalArgumentException;
import fr.maalej.maventest.model.Entry;
import fr.maalej.maventest.webservice.FluxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class FluxController {

    @Autowired
    private FluxService fluxService;

    @GetMapping("/")
    public List<Entry> list() {
        return fluxService.findAll();
    }

    @GetMapping("/{id}")
    public Entry find(@PathVariable("id") Integer id, HttpServletResponse response) {
        return fluxService.find(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id, HttpServletResponse response) {
        fluxService.delete(id);
    }

    @PatchMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Entry entry, HttpServletResponse response) {
        fluxService.update(entry);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody Entry entry, HttpServletResponse response) {
        fluxService.create(entry);
    }
}

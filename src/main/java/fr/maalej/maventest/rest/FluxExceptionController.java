package fr.maalej.maventest.rest;

import fr.maalej.maventest.exceptions.NoSuchElementFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FluxExceptionController {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> exception(IllegalArgumentException exception) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchElementFoundException.class)
    public ResponseEntity<Object> exception(NoSuchElementFoundException exception) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

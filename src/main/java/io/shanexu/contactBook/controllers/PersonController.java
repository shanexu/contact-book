package io.shanexu.contactBook.controllers;

import io.shanexu.contactBook.entities.Person;
import io.shanexu.contactBook.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
@Slf4j
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Person> findAll(String firstName, String lastName, Pageable pageable) {
        log.debug("findAll firstName {} lastName {} pageable {}", firstName, lastName, pageable);
        return personRepository.findByFirstNameOrLastNameAllIgnoreCase(firstName, lastName, pageable);
    }

    @GetMapping("/{id}")
    public Optional<Person> getOne(@PathVariable String id) {
        log.debug("findById {}", id);
        return personRepository.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@Valid @RequestBody Person person) {
        Person newPerson = personRepository.save(person);
        return newPerson;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        personRepository.deleteById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}

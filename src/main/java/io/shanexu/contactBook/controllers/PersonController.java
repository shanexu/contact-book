package io.shanexu.contactBook.controllers;

import io.shanexu.contactBook.entities.Person;
import io.shanexu.contactBook.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
        return personRepository.findByFirstNameOrLastName(firstName, lastName, pageable);
    }

    @GetMapping("/{id}")
    public Optional<Person> getOne(@PathVariable String id) {
        log.debug("findById {}", id);
        return personRepository.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) {
        Person newPerson = personRepository.save(person);
        return newPerson;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        personRepository.deleteById(id);
    }

}

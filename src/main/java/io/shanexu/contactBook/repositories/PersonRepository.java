package io.shanexu.contactBook.repositories;

import io.shanexu.contactBook.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, String> {
    Page<Person> findByFirstNameOrLastNameAllIgnoreCase(String firstName, String lastName, Pageable pageable);
}

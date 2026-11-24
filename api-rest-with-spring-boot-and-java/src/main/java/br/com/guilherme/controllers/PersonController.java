package br.com.guilherme.controllers;

import br.com.guilherme.model.Person;
import br.com.guilherme.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping(
            value = "/{idPerson}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> findById(@PathVariable Long idPerson) {
        return ResponseEntity.ok(personService.findById(idPerson));
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return ResponseEntity.ok(personService.create(person));
    }

    @PutMapping(
            value = {"/{idPerson}"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Person> update(@PathVariable Long idPerson, @RequestBody Person person) {
        return ResponseEntity.ok(personService.updatePerson(idPerson, person));
    }

    @DeleteMapping("/{idPerson}")
    public ResponseEntity<Void> delete(@PathVariable Long idPerson) {
        personService.deletePersonById(idPerson);
        return ResponseEntity.noContent().build();
    }
}

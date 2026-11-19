package br.com.guilherme.controllers;

import br.com.guilherme.model.Person;
import br.com.guilherme.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping(
            value = "/{idPerson}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable Long idPerson) {
        return personService.findById(idPerson);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping(
            value = {"/{idPerson}"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Person update(@PathVariable Long idPerson , @RequestBody Person person) {
        return personService.updatePerson(idPerson, person);
    }

    @DeleteMapping("/{idPerson}")
    public void delete(@PathVariable Long idPerson) {
        personService.deleteById(idPerson);
    }
}

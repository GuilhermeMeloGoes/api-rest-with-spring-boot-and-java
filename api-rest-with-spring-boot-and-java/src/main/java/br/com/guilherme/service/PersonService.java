package br.com.guilherme.service;

import br.com.guilherme.exceptions.ResourceNotFoundException;
import br.com.guilherme.model.Person;
import br.com.guilherme.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    private final Logger LOGGER = Logger.getLogger(PersonService.class.getName());

    @Transactional(readOnly = true)
    public Person findById(Long idPerson) {
        LOGGER.info(String.format("Finding one person by id: %s", idPerson));

        return personRepository.findById(idPerson).orElseThrow(
                () -> new ResourceNotFoundException(String.format("No records found with this id %d", idPerson))
        );
    }

    @Transactional(readOnly = true)
    public List<Person> findAll() {
        LOGGER.info("Finding all people!");

        return personRepository.findAll();
    }

    @Transactional
    public Person create(Person person) {
        LOGGER.info("Create a new Person!");

        return personRepository.save(person);
    }

    @Transactional
    public Person updatePerson(Long idPerson, Person person) {
        LOGGER.info("Update a Person!");

        Person entityPerson = personRepository.getReferenceById(idPerson);

        entityPerson.setFirstName(person.getFirstName());
        entityPerson.setLastName(person.getLastName());
        entityPerson.setAddress(person.getAddress());
        entityPerson.setGender(person.getGender());

        return personRepository.save(entityPerson);
    }

    @Transactional
    public void deletePersonById(Long idPerson) {
        LOGGER.info(String.format("Deleting person with id: %s", idPerson));

        personRepository.findById(idPerson).orElseThrow(
                () -> new ResourceNotFoundException(String.format("No records found with this id %d", idPerson)));

        personRepository.deleteById(idPerson);
    }
}

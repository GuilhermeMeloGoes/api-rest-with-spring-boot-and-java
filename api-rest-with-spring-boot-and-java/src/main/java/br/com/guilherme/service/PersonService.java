package br.com.guilherme.service;

import br.com.guilherme.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong COUNTER = new AtomicLong();

    private final Logger LOGGER = Logger.getLogger(PersonService.class.getName());

    public Person findById(Long idPerson) {
        LOGGER.info(String.format("Finding one person by id: %s", idPerson));

        // Mock data
        Person person = new Person();
        person.setId(COUNTER.incrementAndGet());
        person.setFirstName("Guilherme");
        person.setLastName("Melo");
        person.setGender("Male");
        person.setAddress("Aracaju - Sergipe - Brasil");

        return person;
    }

    public List<Person> findAll() {
        LOGGER.info("Finding all people!");

        // Mock data
        List<Person> listPerson = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            listPerson.add(person);
        }

        return listPerson;
    }

    public Person create(Person person) {
        LOGGER.info("Create a new Person!");
        return person;
    }

    public Person updatePerson(Long idPerson, Person person) {
        LOGGER.info("Update a Person!");
        person.setId(idPerson);
        return person;
    }

    public void deleteById(Long idPerson) {
        LOGGER.info(String.format("Deleting person with id: %s", idPerson));
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(COUNTER.incrementAndGet());
        person.setFirstName("Firstname " + i);
        person.setLastName("Lastname " + i);

        if (i % 2 == 0) {
            person.setGender("Male");
        } else {
            person.setGender("Female");
        }

        person.setAddress("Some adress in Brasil " + i);

        return person;
    }
}

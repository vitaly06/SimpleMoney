package ru.oksei.talisman.simpleMoney.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.talisman.simpleMoney.Models.Person;
import ru.oksei.talisman.simpleMoney.Repositories.PersonRepository;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person login(String email, String password){
        return personRepository.findByEmailAndPassword(email, password);
    }
    @Transactional
    public String registration(Person person){
        if (personRepository.findByEmail(person.getEmail()) != null){
            return "error";
        }
        personRepository.save(person);
        return "okay";
    }

}

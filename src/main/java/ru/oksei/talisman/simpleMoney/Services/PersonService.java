package ru.oksei.talisman.simpleMoney.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.talisman.simpleMoney.Models.Person;
import ru.oksei.talisman.simpleMoney.Repositories.PersonRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPerson(int id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person login(String email, String password){
        return personRepository.findByEmailAndPassword(email, password);
    }
    @Transactional
    public String registration(Person person){
        Person checkPerson = personRepository.findByEmail(person.getEmail());
        if (checkPerson != null){
            System.out.println("error");
            return "error";
        }
        personRepository.save(person);
        System.out.println("Save");
        return "okay";
    }

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }


}

package ru.oksei.talisman.simpleMoney.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oksei.talisman.simpleMoney.Models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByEmailAndPassword(String email, String password);
    Person findByEmail(String email);
}
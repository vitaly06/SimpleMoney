package ru.oksei.talisman.simpleMoney.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oksei.talisman.simpleMoney.Models.LoginResponse;
import ru.oksei.talisman.simpleMoney.Models.Person;
import ru.oksei.talisman.simpleMoney.Services.JwtService;
import ru.oksei.talisman.simpleMoney.Services.PersonService;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class PersonController {
    PersonService personService;
    JwtService jwtService;
    @Autowired
    public PersonController(PersonService personService, JwtService jwtService) {
        this.personService = personService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        Person person = personService.login(email, password);
        if (person != null && person.getPassword().equals(password)) {
            String token = jwtService.generateToken(person.getEmail());
            LoginResponse loginResponse = new LoginResponse(person, token);
            return ResponseEntity.ok().body(loginResponse);
        }
        return ResponseEntity.status(401).body("Неверный email или пароль");
    }

    @PostMapping("/reg")
    public ResponseEntity<?> register(@RequestParam("name") String name, @RequestParam("email") String email,
                                      @RequestParam("password") String password) {
        Person person = new Person(name, email, password);
        System.out.println(person.getEmail());
        System.out.println(person.getPassword());
        String res = personService.registration(person);
        if (res.equals("error")){
            return ResponseEntity.status(401).body("Пользователь с таким email уже зарегистрирован");
        }
        return ResponseEntity.ok().body("Пользователь зарегистрирован");
    }

    @GetMapping("/all")
    public List<Person> getAll() {
        return personService.getAllPersons();
    }
}

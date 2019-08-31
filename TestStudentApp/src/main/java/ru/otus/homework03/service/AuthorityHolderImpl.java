package ru.otus.homework03.service;

import org.springframework.stereotype.Component;
import ru.otus.homework03.domain.Person;

// для авторизации студента
@Component
public class AuthorityHolderImpl implements AuthorityHolder {
    public Person person;
    private Boolean authority;

    public AuthorityHolderImpl() {
        this.person = new Person();
    }

    public Boolean getAuthority(String lastname, String firstname) {
        // TODO
        // для упрощения возвращаем всегда true
        person.setLastname(lastname);
        person.setFirstname(firstname);
        authority = true;
        return authority;
    }

    public String getLastName() {
        return person.getLastname();
    }

    public String getFistName() {
        return person.getFirstname();
    }
}
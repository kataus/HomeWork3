package ru.otus.homework03.domain;

public class Person {
    private String lastname;
    private String firstname;

    public void setLastname(String surname) {
        this.lastname = surname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }
}

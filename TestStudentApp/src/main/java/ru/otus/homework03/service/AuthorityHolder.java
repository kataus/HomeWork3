package ru.otus.homework03.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthorityHolder {
    Boolean getAuthority(String lastname, String firstname);
    String getLastName();
    String getFistName();
}

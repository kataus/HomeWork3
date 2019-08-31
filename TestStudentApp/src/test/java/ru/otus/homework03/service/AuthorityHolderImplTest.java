package ru.otus.homework03.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("Тест сервиса авторизации")
class AuthorityHolderImplTest {
    @Autowired
    AuthorityHolder authorityHolder;

    @BeforeEach
    void setUp() {
        authorityHolder.getAuthority("Hoeller", "Juergen");
    }

    @Test
    void getAuthority() {
        assertEquals(true, authorityHolder.getAuthority("Hoeller", "Juergen"));
    }

    @Test
    void getLastName() {
        assertEquals("Hoeller", authorityHolder.getLastName());
    }

    @Test
    void getFistName() {
        assertEquals("Juergen", authorityHolder.getFistName());
    }
}
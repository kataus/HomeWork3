package ru.otus.homework03.service;

import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public interface AppModuleLauncher {
    void startTest() throws IOException;

    /* альтернативный вариант запуска
    @PostConstruct
    default void init() throws IOException {
        startTest();
    }*/
}

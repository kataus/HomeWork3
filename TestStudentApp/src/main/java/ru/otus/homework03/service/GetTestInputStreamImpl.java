package ru.otus.homework03.service;

import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class GetTestInputStreamImpl implements GetTestInputStream {
    @Override
    public InputStream getInputStream(Class clazz, String fileName) {
        // файл с вопросами/ответами {№ правильного ответа; вопрос; ответ1; ответ2; ответ3; ответ4 ... ; ответN }
        InputStream inputStream = clazz.getClassLoader().getResourceAsStream(fileName);
        return inputStream;
    }
}

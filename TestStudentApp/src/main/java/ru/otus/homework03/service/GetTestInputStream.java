package ru.otus.homework03.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public interface GetTestInputStream {
    InputStream getInputStream (Class clazz, String fileName);
}

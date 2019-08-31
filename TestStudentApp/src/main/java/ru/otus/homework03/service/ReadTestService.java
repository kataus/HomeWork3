package ru.otus.homework03.service;

import org.springframework.stereotype.Service;
import ru.otus.homework03.domain.Question;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

@Service
public interface ReadTestService {
    HashMap<Integer, Question> readTestProcess(InputStream inputStream) throws IOException;
    String decodeHexSymbol (String text);
}
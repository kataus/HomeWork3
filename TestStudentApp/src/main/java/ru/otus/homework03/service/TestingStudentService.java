package ru.otus.homework03.service;

import org.springframework.stereotype.Service;
import ru.otus.homework03.domain.Question;
import ru.otus.homework03.domain.TestResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

@Service
public interface TestingStudentService {
    // BufferedReader пробрасываем для возможности интеграционного тестирования
    HashMap<Integer, TestResult> testingProcess(HashMap<Integer, Question> teststudent, BufferedReader brInput) throws IOException;
}

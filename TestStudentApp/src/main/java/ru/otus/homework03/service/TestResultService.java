package ru.otus.homework03.service;

import org.springframework.stereotype.Service;
import ru.otus.homework03.domain.TestResult;
import java.util.HashMap;

@Service
public interface TestResultService {
    void getTestResult(HashMap<Integer, TestResult> testResults);
}
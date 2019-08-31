package ru.otus.homework03.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.homework03.domain.Question;
import ru.otus.homework03.domain.TestResult;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("Тест сервиса тестирования студентов - 'TestingStudentService'")
class TestingStudentServiceImplTest {
    @Autowired
    TestingStudentService testingStudentService;

    @Test
    @DisplayName("корректно работает тест на всех правильных ответах")
    void testingProcessValidAnswer() throws IOException {
        HashMap<Integer, Question> testStudent = getTestStudent();
        // данные для ввода - правильные ответы на тестовый файл из 5-ти вопросов
        StringBuilder sb = new StringBuilder();
        sb.append("1").append('\n');
        sb.append("2").append('\n');
        sb.append("3").append('\n');
        sb.append("4").append('\n');
        sb.append("5").append('\n');
        String data = sb.toString();
        //Оборачиваем строку в класс ByteArrayInputStream
        InputStream is = new ByteArrayInputStream(data.getBytes());
        //подменяем in
        System.setIn(is);
        /* 3. Задаем вопросы, сохраняем ответы*/
        BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, TestResult> testResults = testingStudentService.testingProcess(testStudent, brInput);
        // сброс System.in
        System.setIn(System.in);
        // Проверка результатов
        for (Map.Entry<Integer, TestResult> entry: testResults.entrySet()) {
            TestResult testResult = entry.getValue();
            System.out.println(testResult.getRigthAnswer() + " : " +  testResult.isAnswerResult());
            assertEquals(true, testResult.isAnswerResult());
        }
    }

    @Test
    @DisplayName("корректно работает тест на всех неправильных ответах")
    void testingProcessInvalidAnswer() throws IOException {
        HashMap<Integer, Question> testStudent = getTestStudent();
        // данные для ввода - правильные ответы на тестовый файл из 5-ти вопросов
        StringBuilder sb = new StringBuilder();
        sb.append("2").append('\n');
        sb.append("3").append('\n');
        sb.append("4").append('\n');
        sb.append("5").append('\n');
        sb.append("1").append('\n');
        String data = sb.toString();
        //Оборачиваем строку в класс ByteArrayInputStream
        InputStream is = new ByteArrayInputStream(data.getBytes());
        //подменяем in
        System.setIn(is);
        /* 3. Задаем вопросы, сохраняем ответы*/
        BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, TestResult> testResults = testingStudentService.testingProcess(testStudent, brInput);
        // сброс System.in
        System.setIn(System.in);
        // Проверка результатов
        for (Map.Entry<Integer, TestResult> entry: testResults.entrySet()) {
            TestResult testResult = entry.getValue();
            System.out.println(testResult.getRigthAnswer() + " : " +  testResult.isAnswerResult());
            assertEquals(false, testResult.isAnswerResult());
        }
    }

    // Заполняем HashMap вопросами-ответами
    private HashMap<Integer, Question> getTestStudent() {
        // 1;Вопрос1;Ответ1.1;Ответ1.2;Ответ1.3;Ответ1.4;Ответ1.5
        HashMap<Integer, Question> testStudent = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            ArrayList<String> answers = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                answers.add("Answer"+ (i+1) + "." + (j+1));
            }
            testStudent.put(i,  new Question("Ask"+ i+1, answers, i+1));
        }
        return testStudent;
    }
}
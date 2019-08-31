package ru.otus.homework03.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.homework03.domain.TestResult;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест сервиса отображения результатов - 'TestResultService'")
@RunWith(SpringRunner.class)
@SpringBootTest
class TestResultServiceImplTest {
    @Autowired
    TestResultService testResultService;

    @Test
    @DisplayName("корректно работает отображение результата")
    void getTestResult() {
        // сохраняем System.out
        PrintStream consStream = System.out;
        // динамический массив
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //адаптер для PrintStream
        PrintStream stream = new PrintStream(outStream);
        // подменяем System.out
        System.setOut(stream);
        // готовим данные для теста
        HashMap<Integer, TestResult> testResults = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            String ask = "Ask"+ (i+1);
            String rigthAnswer = "Answer" + (i+1) + "." + (i+1);
            boolean answerResult = true;
            testResults.put(i, new TestResult(ask, rigthAnswer, answerResult));
        }
        // запускаем тест
        testResultService.getTestResult(testResults);
        // восстанавливаем System.out
        System.setOut(consStream);
        // результат построчно в массив
        String[] array = outStream.toString().split("\r\n");
        assertEquals(true,array.length == 25);
        for (int i = 4; i < array.length-4; i+=4) {
            assertEquals("Question "+(i/4)+":",array[i]);
            assertEquals(" (Ask"+(i/4)+ ") : ", array[i+1]);
            assertEquals(" - correct answer ",array[i+2]);
            System.out.println(array[i]);
            System.out.println(array[i+1]);
            System.out.println(array[i+2]);
        }
        assertEquals("Congratulations! Test passed. Correct answers: 5 of 5 ", array[24]);
    }
}
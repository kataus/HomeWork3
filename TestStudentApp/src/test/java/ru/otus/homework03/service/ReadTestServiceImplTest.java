package ru.otus.homework03.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.homework03.domain.Question;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("Тест сервиса получения файла с тестом в поток - 'GetTestInputStream'")
class ReadTestServiceImplTest {
    @Autowired
    ReadTestService readTestService;

    @Test
    @DisplayName("Файл считался правильно")
    void readTestProcess() throws IOException {
        // получаем HashMap из 'test/resource/QuestionsTesting.csv'
        String fileName = "QuestionsTesting.csv";
        System.out.println(this.getClass().getClassLoader().getResource(fileName));
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        HashMap<Integer, Question> testStudent = readTestService.readTestProcess(inputStream);
        // Проверка корректности заполнения
        int i = 1;
        for (Map.Entry<Integer, Question> entry: testStudent.entrySet()) {
            Question question = entry.getValue();
            String curLine = i + ";Ask"+ i + ";Answer"+ i +".1;Answer"+ i + ".2;Answer"+ i+".3;Answer"+ i + ".4;Answer" + i + ".5";
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < question.getAnswers().size(); j++) {
                if (j < question.getAnswers().size()-1)
                    sb.append(question.getAnswers().get(j)).append(";");
                else
                    sb.append(question.getAnswers().get(j));
            }
            String s = question.getCorrectAnswer() + ";" + question.getAsk() + ";" + sb.toString();
            System.out.println(s);
            assertEquals(curLine, s);
            i++;
        }
    }

    @Test
    @DisplayName("Декодирование hex-символов работает")
    void decodeHexSymbol() {
        String s = readTestService.decodeHexSymbol("asdfghjkl\\u003B1234567890\\u003B");
        assertEquals("asdfghjkl;1234567890;", s);
    }
}
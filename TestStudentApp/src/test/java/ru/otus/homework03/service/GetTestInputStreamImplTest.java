package ru.otus.homework03.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("Тест сервиса получения файла с тестом в поток - 'GetTestInputStream'")
class GetTestInputStreamImplTest {
    @Autowired
    GetTestInputStream getTestInputStream;

    @Test
    @DisplayName("csv файл читается правильно'")
    void getInputStream() throws IOException {
        InputStream inputStream = getTestInputStream.getInputStream(this.getClass(),"QuestionsTesting.csv");
        BufferedReader brFile = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String s;
        int i=1;
        while ((s=brFile.readLine()) != null) {
            String curLine = i + ";Ask"+ i + ";Answer"+ i +".1;Answer"+ i + ".2;Answer"+ i+".3;Answer"+ i + ".4;Answer" + i + ".5";
            System.out.println(s);
            assertEquals(curLine, s);
            i++;
        }
        inputStream.close();
        brFile.close();
    }
}
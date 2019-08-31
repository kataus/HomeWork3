package ru.otus.homework03.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.*;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("Интеграционный тест приложения")
class AppModuleLauncherImplTest {
    @Autowired
    AppModuleLauncher appModuleLauncher;

    @Test
    @DisplayName("Тестирование студента работает корректно")
    void startTestIntegr() throws IOException {
        // для теста используем en_US
        Locale locale;
        locale = new Locale("en", "US");
        locale.setDefault(locale);
        // данные для ввода - правильные ответы на тестовый файл из 5-ти вопросов (csv берем из test/resources)
        StringBuilder sb = new StringBuilder();
        sb.append("Juergen").append('\n');
        sb.append("Hoeller").append('\n');
        sb.append("1").append('\n');
        sb.append("2").append('\n');
        sb.append("3").append('\n');
        sb.append("4").append('\n');
        sb.append("5").append('\n');
        String data = sb.toString();
        //Оборачиваем строку в класс ByteArrayInputStream
        InputStream is = new ByteArrayInputStream(data.getBytes());
        // динамический массив для System.out
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //адаптер для PrintStream
        PrintStream stream = new PrintStream(outStream);
        // сохраняем System.in, out
        InputStream consIn = System.in;
        PrintStream consOut = System.out;
        //подменяем in, out
        System.setIn(is);
        System.setOut(stream);
        // Запуск теста
        appModuleLauncher.startTest();
        // восстановление System in, out
        System.setIn(consIn);
        System.setOut(consOut);
        // проверка результата
        // результат построчно в массив
        String[] array = outStream.toString().split("\r\n");
        // длина массива строк
        assertEquals(82,array.length);
        // Запрос ФИО, приветствие
        assertEquals("Enter your name:", array[1]);
        assertEquals("Enter last name:", array[2]);
        assertEquals("Hello, Hoeller!", array[4]);
        System.out.println(array[1]);
        System.out.println(array[2]);
        System.out.println(array[4]);
        // к-во правильных ответов
        assertEquals("Number of correct answers score = 5 ", array[5]);
        // Проверка вывода по тестированию
        for (int i = 7; i < array.length-31; i+=10) {
            assertEquals("Question " + (i+3)/10 + " of 5 ",array[i]);
            assertEquals("Ask" + (i+3)/10, array[i + 1]);
            assertEquals("1. Answer" + (i+3)/10 + ".1", array[i+3]);
            assertEquals("2. Answer" + (i+3)/10 + ".2", array[i+4]);
            assertEquals("3. Answer" + (i+3)/10 + ".3", array[i+5]);
            assertEquals("4. Answer" + (i+3)/10 + ".4", array[i+6]);
            assertEquals("5. Answer" + (i+3)/10 + ".5", array[i+7]);
            assertEquals("Enter the number of the correct answer (1..5): ",array[i + 9]);
            System.out.println(array[i]);
            System.out.println(array[i+1]);
            System.out.println(array[i+3]);
            System.out.println(array[i+4]);
            System.out.println(array[i+5]);
            System.out.println(array[i+6]);
            System.out.println(array[i+7]);
            System.out.println(array[i+9]);
        }
        // проверка вывода результов тестирования
        for (int i = 61; i < array.length-4; i+=4) {
            assertEquals("Question " + (i-57)/4 + ":",array[i]);
            assertEquals(" (Ask" + (i-57)/4 + ") : ", array[i+1]);
            assertEquals(" - correct answer ",array[i+2]);
            System.out.println(array[i]);
            System.out.println(array[i+1]);
            System.out.println(array[i+2]);
        }
        assertEquals("Congratulations! Test passed. Correct answers: 5 of 5 ", array[81]);
    }
}
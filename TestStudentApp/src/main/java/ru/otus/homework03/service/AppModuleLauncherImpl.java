package ru.otus.homework03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.homework03.domain.Question;
import ru.otus.homework03.domain.TestResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;

@Component
public class AppModuleLauncherImpl implements AppModuleLauncher {

    final private AuthorityHolder authorityHolder;
    final private GetTestInputStream getTestInputStream;
    final private ReadTestService readTestService;
    final private TestingStudentService testingStudentService;
    final private TestResultService testResultService;
    final private MessageSource ms;

    @Value("${files.testFileName}")
    String fileName;

    @Autowired
    public AppModuleLauncherImpl(GetTestInputStream getTestInputStream,
                                 AuthorityHolder authorityHolder,
                                 ReadTestService readTestService,
                                 TestingStudentService testingStudentService,
                                 TestResultService testResultService, MessageSource ms) {
        this.authorityHolder = authorityHolder;
        this.getTestInputStream = getTestInputStream;
        this.readTestService = readTestService;
        this.testingStudentService = testingStudentService;
        this.testResultService = testResultService;
        this.ms = ms;
    }

    public void startTest() throws IOException {
        // для чтения с клавиатуры
        BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
        // спросить у пользователя фамилию и имя
        System.out.println();
        System.out.println(ms.getMessage("input.firstname", null, Locale.getDefault()));
        String lastname = brInput.readLine();
        System.out.println(ms.getMessage("input.lastname", null, Locale.getDefault()));
        String firstname = brInput.readLine();
        // авторизация
        if (!authorityHolder.getAuthority(lastname, firstname)) {
            System.out.println("Студент не авторизован");
            System.out.println(ms.getMessage("input.notAuthorityMsg", new String[]{authorityHolder.getFistName()+" "+authorityHolder.getLastName()}, Locale.getDefault()));
            brInput.close();
        } else {
            /* 1. Получаем stream с файлом вопросов-ответов*/
            //String fileName;
            String curLocal = Locale.getDefault().toString();
            if (curLocal.equals("en_US"))
                fileName = fileName + ".csv";
            else
                fileName = fileName + "_" + curLocal + ".csv";
            InputStream inputStream = getTestInputStream.getInputStream(this.getClass(), fileName);
            /* 2. Заполняем тест вопросами*/
            HashMap<Integer, Question> testStudent = readTestService.readTestProcess(inputStream);
            /* 3. Задаем вопросы, сохраняем ответы*/
            HashMap<Integer, TestResult> testResults = testingStudentService.testingProcess(testStudent, brInput);
            /* 4. выводим результаты тестирования*/
            if (testResults != null)
                testResultService.getTestResult(testResults);
        }
        brInput.close();
    }
}

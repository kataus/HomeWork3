package ru.otus.homework03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.homework03.domain.Question;
import ru.otus.homework03.domain.TestResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class TestingStudentServiceImpl implements TestingStudentService {
    // к-во правильных ответов для зачета
    @Value("${teststudent.countAnswPassed}")
    String countAnswPassed;
    final private MessageSource ms;
    final private AuthorityHolder authorityHolder;

    @Autowired
    public TestingStudentServiceImpl(MessageSource ms, AuthorityHolder authorityHolder) {
        this.ms = ms;
        this.authorityHolder = authorityHolder;
    }

    /*3. Задаем вопросы, сохраняем ответы*/
    public HashMap<Integer, TestResult> testingProcess(HashMap<Integer, Question> teststudent, BufferedReader brInput) throws IOException {
        int key=0;
        // тестирование
        System.out.println();
        System.out.println(ms.getMessage("input.user", new String[]{authorityHolder.getFistName()}, Locale.getDefault()));
        System.out.printf(ms.getMessage("input.cntTrueQuestion", null, Locale.getDefault()), countAnswPassed);
        System.out.println();
        // задаем вопросы
        for (Map.Entry<Integer, Question> entry : teststudent.entrySet()) {
            key = entry.getKey();
            Question question = entry.getValue();
            // вопрос
            System.out.printf(ms.getMessage("input.questionOf", null, Locale.getDefault()), key + 1, teststudent.size());
            System.out.println(question.getAsk());
            System.out.println();
            // перечисляем все ответы на выбор
            int i = 1;
            for (String answer : question.getAnswers()) {
                System.out.println(i++ + ". " + answer);
            }
            System.out.println();
            // ждем ответа
            System.out.printf(ms.getMessage("input.enterNumbCorrAnsw", null, Locale.getDefault()), question.getAnswers().size());
            // бесконечный цикл пока пользователь не наберет цифру в диапазоне ответов
            int k = 0;
            do {
                String str = brInput.readLine();
                try {
                    k = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                }
                // проверяем введенный символ на цифру в заданном диапазоне
                if (k == 0 || k > question.getAnswers().size()) {
                    System.out.printf(ms.getMessage("input.errEnterNumbFrom", null, Locale.getDefault()), question.getAnswers().size());
                    k = 0;
                }
            } while (k == 0);
            // сохраняем ответ
            question.setStudentAnswer(k);
        }
        // Заполняем результаты тестирования
        return setTestResults(teststudent);
    }

    // результаты тестирования
    HashMap<Integer, TestResult> setTestResults(HashMap<Integer, Question> teststudent) {
        HashMap<Integer, TestResult> testResults = new HashMap<Integer, TestResult>();
        int key=0;
        for (Map.Entry<Integer, Question> entry: teststudent.entrySet()) {
            key = entry.getKey();
            Question question = entry.getValue();
            String rigthAnswer = question.getAnswers().get(question.getCorrectAnswer()-1);
            boolean answerResult = question.getAnswerResult();
            testResults.put(key, new TestResult(question.getAsk(), rigthAnswer, answerResult));
        }
        return testResults;
    }
}


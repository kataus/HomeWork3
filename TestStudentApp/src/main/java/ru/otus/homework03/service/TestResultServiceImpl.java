package ru.otus.homework03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.homework03.domain.TestResult;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class TestResultServiceImpl implements TestResultService {
    // к-во правильных ответов для зачета
    @Value("${teststudent.countAnswPassed}")
    int countAnswPassed;

    final private MessageSource ms;
    final private AuthorityHolder authorityHolder;

    @Autowired
    public TestResultServiceImpl(MessageSource ms, AuthorityHolder authorityHolder) {
        this.ms = ms;
        this.authorityHolder = authorityHolder;
    }

    /*4. выводим результаты тестирования*/
    public void getTestResult(HashMap<Integer, TestResult> testResults) {
        int key=0;
        // к-во правильных ответов
        int trueAnswers=0;
        System.out.println();
        System.out.printf(ms.getMessage("output.student", null, Locale.getDefault()), authorityHolder.getFistName(), authorityHolder.getLastName());
        System.out.println(ms.getMessage("output.resTesting", null, Locale.getDefault()));
        for (Map.Entry<Integer, TestResult> entry: testResults.entrySet()) {
            key = entry.getKey();
            TestResult testResult = entry.getValue();

            if (testResult.isAnswerResult()) {
                trueAnswers++;
                System.out.printf(ms.getMessage("output.rightAnswer", null, Locale.getDefault()), key+1, testResult.getAsk());
            } else
                System.out.printf(ms.getMessage("output.wrongAnswer", null, Locale.getDefault()), key+1, testResult.getAsk(), testResult.getRigthAnswer());
        }
        System.out.println();
        if (trueAnswers >= countAnswPassed)
            System.out.printf(ms.getMessage("output.congrtlnTest", null, Locale.getDefault()), trueAnswers, testResults.size());
        else
            System.out.printf(ms.getMessage("output.wrongTest", null, Locale.getDefault()), trueAnswers, testResults.size());
    }
}


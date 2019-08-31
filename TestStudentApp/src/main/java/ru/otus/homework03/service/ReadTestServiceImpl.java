package ru.otus.homework03.service;

import org.springframework.stereotype.Component;
import ru.otus.homework03.domain.Question;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReadTestServiceImpl implements ReadTestService {
    /* 2. Заполняем тест вопросами*/
    public HashMap<Integer, Question> readTestProcess(InputStream inputStream) throws IOException {
        // создаем новый тест
        HashMap<Integer, Question> teststudent = new HashMap<Integer, Question>();
        // для чтение файла с ресурсов
        BufferedReader brFile = null;
        brFile = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        // заполняем list построчно из файла
        String s;
        int key=0;
        while ((s=brFile.readLine()) != null) {
            int correctAnswer=0;
            String ask = new String();
            ArrayList<String> answers = new ArrayList<String>();
            // правильный ответ и вопросы по одной группе складываем в массив
            String[] array = s.split(";");
            // Заменяем коды символов на символы (перенос строки, ;)
            for (int i = 0; i < array.length; i++) {
                array[i] = decodeHexSymbol(array[i]);
            }
            // парсинг строки
            for (int i = 0; i < array.length; i++) {
                // Сохраняем номер правильного ответа
                if (i == 0)
                    correctAnswer = Integer.parseInt(array[i]);
                // Заголовок вопроса
                if (i == 1)
                    ask = array[i];
                if (i > 1)
                    answers.add(array[i]);
            }
            Question question = new Question(ask, answers, correctAnswer);
            teststudent.put(key, question);
            key++;
        }
        inputStream.close();
        brFile.close();
        return teststudent;
    }

    /* декодирование "\u000A" в "\n" "\u003B" в ";" для многострочных вопросов (например фрагмент кода) */
    public String decodeHexSymbol (String text) {
        Pattern p = Pattern.compile("\\\\u([0-9A-F]{4})", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String s = m.group(1);
            char c = (char)Integer.parseInt(s,16);
            m.appendReplacement(sb, String.valueOf(c));
        }
        m.appendTail(sb);
        return sb.toString();
    }
}

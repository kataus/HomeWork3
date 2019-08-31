package ru.otus.homework03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.homework03.service.AppModuleLauncher;
import java.io.IOException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(Main.class);

        // запуск тестирования
        final AppModuleLauncher appModuleLauncher = context.getBean(AppModuleLauncher.class);
        appModuleLauncher.startTest();
    }
}

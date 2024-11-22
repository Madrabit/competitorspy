package ru.madrabit.competitorspy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.madrabit.competitorspy.config.SeleniumHandler;

@SpringBootApplication
@Slf4j
public class CompetitorSpyApplication {

    public static void main(String[] args) {
        SeleniumHandler seleniumHandler = new SeleniumHandler();
        seleniumHandler.start(false);
        seleniumHandler.openPage("https://isbd.ru");
        log.info("Opened main page: {}", "https://isbd.ru");
        String pageSource = seleniumHandler.getPageSource();
        System.out.println(pageSource);
        seleniumHandler.stop();
        SpringApplication.run(CompetitorSpyApplication.class, args);
    }

}

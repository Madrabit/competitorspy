package ru.madrabit.competitorspy.parser;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import ru.madrabit.competitorspy.config.SeleniumHandler;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParserAdminServiceTest {

    @Autowired
    ParserService service;
    String pageSource;

    @Autowired
    SeleniumHandler seleniumHandler;
    @BeforeEach
    void setUp() {
        seleniumHandler.start();
        seleniumHandler.openPage("https://isbd.ru/raspisanie");
        pageSource = seleniumHandler.getPageSource();
//        System.out.println("Page source:\n" + pageSource);
//        WebElement element = seleniumHandler.driver.findElement(By.cssSelector("#event_filter__wrapper > div > div.event_filter__result > div.event_filter__result__by_direction > table"));
//        if (element == null) {
//            throw new IllegalStateException("Parent element not found");
//        }
//        System.out.println("Found elements: " + element);
//        List<WebElement> elements = element.findElements(By.cssSelector("#event_filter__wrapper > div > div.event_filter__result > " +
//                                                                        "div.event_filter__result__by_direction > table > tbody > " +
//                                                                        "tr:nth-child(3) > td:nth-child(1) > a"));
//        for (WebElement webElement : elements) {
//            System.out.println(webElement.getText());
//        }
    }

    @AfterEach
    void tearDown() {
        seleniumHandler.stop();
    }
    @Test
    void getTitles() {
        List<WebElement> titles = service.getTitles();
        for (WebElement title : titles) {
            System.out.println(title.getText());
        }

    }

    @Test
    void getProductsName() {
        List<WebElement> allProductsTitle = service.getAllProductsTitle();
        System.out.println(allProductsTitle.size());
        for (WebElement element : allProductsTitle) {
            System.out.println(element.getText());
        }


    }
}
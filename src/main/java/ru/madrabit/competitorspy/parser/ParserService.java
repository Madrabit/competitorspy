package ru.madrabit.competitorspy.parser;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;
import ru.madrabit.competitorspy.config.SeleniumHandler;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParserService {
    private final SeleniumHandler handler;

    public List<WebElement> getTitles() {
        WebElement parentOfTitles = handler.getElement("table.event_filter__table");
        List<WebElement> titles = parentOfTitles.findElements(By.className("event_filter__table_taget_title"));
        return titles;
    }

    public List<WebElement> getAllProductsTitle() {
        WebElement parentOfTitles = handler.getElement("table.event_filter__table");
        List<WebElement> tableElements = parentOfTitles.findElements(By.cssSelector("*"));
        List<WebElement> products = new ArrayList<>();
        boolean skipFirstRow = true;
        List<WebElement> cells = parentOfTitles.findElements(By.cssSelector("td.event_filter__table_taget_title"));
        for (WebElement tableElement : tableElements) {
            if (!tableElement.getTagName().equals("tr")) {
                continue;
            }
            if (skipFirstRow) {
                skipFirstRow = false;
                continue;
            }
            if (!cells.isEmpty()) {
                System.out.println();; // Останавливаем цикл, если нашли <td> с нужным классом
            }

            products.add(tableElement);

        }
        return products;
    }



}

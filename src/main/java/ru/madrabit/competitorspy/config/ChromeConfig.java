package ru.madrabit.competitorspy.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChromeConfig {


    static ChromeOptions getChromeOptions(boolean headlessMode) {
        ChromeOptions options = new ChromeOptions()
                .addArguments("--lang=ru")
                .addArguments("start-maximized");
        if (headlessMode) {
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");

        }
        return options;

    }
}

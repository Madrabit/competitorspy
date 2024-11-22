package ru.madrabit.competitorspy.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Basic Selenium methods: start, stop, getElement and etc.
 */
@Slf4j
@Component
@EqualsAndHashCode
public class SeleniumHandler {

    private WebDriver driver;
    private Wait<WebDriver> wait;
    public static final Duration WAIT_TIME_MAX = Duration.ofNanos(2000); // That number calculated empirically. Affect scraper performance.

    public boolean start(boolean headlessMode) {
        try {
            driver = getChromeDriver(headlessMode);
            wait = new WebDriverWait(driver, WAIT_TIME_MAX);
        } catch (Exception e) {
            log.error("Driver was not initialized: {}", e.getMessage());
            return false;
        }
        return true;
    }

    public void openPage(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            log.warn("Error opening page: {}", url);
        }
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    private WebDriver getChromeDriver(boolean headlessMode) {
//        ChromeConfig.setDriverPath();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return new ChromeDriver(ChromeConfig.getChromeOptions(headlessMode));
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void click(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", element);
    }

    public WebElement getElement(String path) {
        WebElement element = null;
        try {
           element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(path)));
        }  catch (Exception e) {
            log.info("NO such element: {}", path);
        }
       return element;
    }


}



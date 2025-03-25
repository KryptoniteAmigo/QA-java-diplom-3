package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

    public static WebDriver getWebDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/WebDriver/bin/chromedriver-win64/chromedriver.exe");
                return new ChromeDriver();

            case "yandex":
                System.setProperty("webdriver.chrome.driver", "/WebDriver/bin/ydriver-win64//yandexdriver.exe");
                return new ChromeDriver();

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }
}


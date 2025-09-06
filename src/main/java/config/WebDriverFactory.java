package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverFactory {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = WebDriverFactory.class
                .getResourceAsStream("/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить config.properties", e);
        }
    }

    public static WebDriver getWebDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                String chromeDriverPath = properties.getProperty("chrome.driver.path");
                if (chromeDriverPath == null || chromeDriverPath.isEmpty()) {
                    throw new IllegalArgumentException("Не прописан chrome.driver.path в config.properties");
                }
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                return new ChromeDriver();

            case "yandex":
                String yandexDriverPath = properties.getProperty("yandex.driver.path");
                if (yandexDriverPath == null || yandexDriverPath.isEmpty()) {
                    throw new IllegalArgumentException("Не прописан yandex.driver.path в config.properties");
                }
                System.setProperty("webdriver.chrome.driver", yandexDriverPath);
                return new ChromeDriver();

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }
}


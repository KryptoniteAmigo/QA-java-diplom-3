package page_object;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private final String mainUrl = "https://stellarburgers.nomoreparties.site/";
    private final String url = "https://stellarburgers.nomoreparties.site/login";

    private final By loginButton = By.xpath(".//button[contains(@class,'button_button__33qZ0')and text()='Войти']");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By loginField = By.xpath(".//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем веб-страницу с авторизацией пользователя")
    public void open() {
        driver.get(url);
    }

    @Step("Вводим значение в поле 'Email'")
    public void sendValueEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Вводим значение в поле 'Пароль'")
    public void sendValuePasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажимаем на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Проверяем, что отображается форма авторизации с заголовком 'Вход'")
    public boolean isLoginFieldVisible() {
        return driver.findElement(loginField).isDisplayed();
    }

    @Step("Авторизация через форму входа")
    public void loginUser(String email, String password){
        sendValueEmailField(email);
        sendValuePasswordField(password);
        clickLoginButton();
        boolean element = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe(mainUrl));
        Assert.assertEquals("Должен быть переход на главную страницу", mainUrl, driver.getCurrentUrl());
    }
}

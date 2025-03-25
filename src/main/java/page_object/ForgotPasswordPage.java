package page_object;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private WebDriver driver;
    private LoginPage loginPage;
    private final String url = "https://stellarburgers.nomoreparties.site/forgot-password";
    private final String mainUrl = "https://stellarburgers.nomoreparties.site/";

    private final By loginButton = By.xpath(".//a[contains(@class,'Auth_link__1fOlj')and text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем веб-страницу 'Забыли пароль'")
    public void open() {
        driver.get(url);
    }

    @Step("Нажимаем на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Авторизуемся через кнопку 'Вход' в разделе 'Забыли пароль'")
    public void loginUserWithLoginButton(String email, String password){
        open();
        clickLoginButton();
        loginPage = new LoginPage(driver);
        loginPage.sendValueEmailField(email);
        loginPage.sendValuePasswordField(password);
        loginPage.clickLoginButton();
        boolean element = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe(mainUrl));
        Assert.assertEquals("Должен быть переход на главную страницу", mainUrl, driver.getCurrentUrl());
    }
}

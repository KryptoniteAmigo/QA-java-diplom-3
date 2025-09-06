package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;
    private LoginPage loginPage;
    private final String url = "https://stellarburgers.nomoreparties.site/register";

    private final By signUpButton = By.xpath(".//button[contains(@class,'button_button__33qZ0')and text()='Зарегистрироваться']");
    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By incorrectPasswordError = By.xpath(".//p[contains(@class,'input__error')and text()='Некорректный пароль']");
    private final By loginForm = By.cssSelector("div.Auth_login__3hAey");
    private final By loginButton = By.xpath(".//a[contains(@class,'Auth_link__1fOlj')and text()='Войти']");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем страницу с регистрацией пользователя")
    public void open() {
        driver.get(url);
    }

    @Step("Вводим значение в поле 'Имя'")
    public void sendValueNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Вводим значение в поле 'Email'")
    public void sendValueEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Вводим значение в поле 'Пароль'")
    public void sendValuePasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажимаем кнопку 'Зарегистрироваться'")
    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    @Step("Нажимаем кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Проверяем, что есть уведомление об ошибке с некорректным паорлем")
    public boolean isIncorrectPasswordErrorVisible() {
        return !driver.findElements(incorrectPasswordError).isEmpty();
    }

    @Step("Проверяем, что форма входа отображается на странице")
    public boolean isLoginFormVisible() {
        return driver.findElement(loginForm).isDisplayed();
    }

    @Step("Создание нового пользователя")
    public void signUpUser(String name, String email, String password) {
        open();
        sendValueNameField(name);
        sendValueEmailField(email);
        sendValuePasswordField(password);
        clickSignUpButton();
    }

    @Step("Авторизация пользователя через кнопку 'Вход'")
    public void loginUserWithLoginButton(String email, String password) {
        open();
        clickLoginButton();
        loginPage = new LoginPage(driver);
        loginPage.loginUser(email, password);
    }
}

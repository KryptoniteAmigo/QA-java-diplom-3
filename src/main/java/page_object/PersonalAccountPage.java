package page_object;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private final String url = "https://stellarburgers.nomoreparties.site/account/profile";
    private final String mainUrl = "https://stellarburgers.nomoreparties.site/";
    private final String loginUrl = "https://stellarburgers.nomoreparties.site/login";

    private final By personalAccountButton = By.xpath(".//p[contains(@class,'AppHeader_header__linkText')and text()='Личный Кабинет']");
    private final By profileButton = By.xpath(".//a[contains(@class,'Account_link__2ETsJ')and text()='Профиль']");
    private final By exitButton = By.xpath(".//button[contains(@class,'Account_button__14Yp3')and text()='Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаем на кнопку 'Личный Кабинет'")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажимаем кнопку 'Выход'")
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }

    @Step("Проверяем отображение кнопки 'Профиль'")
    public boolean isProfileButtonVisible() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
        return driver.findElement(profileButton).isDisplayed();
    }

    @Step("Делаем переход в 'Личный кабинет'")
    public void movingIntoPersonalAccount(String email, String password)  {
        loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.loginUser(email, password);
        clickPersonalAccountButton();
        boolean element = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe(url));
        Assert.assertEquals("Должен быть переход на страницу профиля", url, driver.getCurrentUrl());
    }

    @Step("Делаем переход на страницу с конструктором")
    public void movingIntoConstructor() {
        mainPage = new MainPage(driver);
        mainPage.clickConstructorButton();
        boolean element = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe(mainUrl));
        Assert.assertEquals("Должен быть переход на главную страницу", mainUrl, driver.getCurrentUrl());
    }

    @Step("Делаем переход на основную страницу по клику логотипа 'Stellar Burgers'")
    public void movingByMainLogo() {
        mainPage = new MainPage(driver);
        mainPage.clickMainLogoButton();
        boolean element = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe(mainUrl));
        Assert.assertEquals("Должен быть переход на главную страницу", mainUrl, driver.getCurrentUrl());
    }

    @Step("Выходим из своего аккаунта")
    public void exitFromProfile() {
        clickExitButton();
        boolean element = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe(loginUrl));
        Assert.assertEquals("Должен быть переход на страницу авторизации", loginUrl, driver.getCurrentUrl());
    }

}

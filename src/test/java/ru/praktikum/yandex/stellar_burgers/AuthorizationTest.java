package ru.praktikum.yandex.stellar_burgers;

import config.WebDriverFactory;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page_object.ForgotPasswordPage;
import page_object.MainPage;
import page_object.RegisterPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AuthorizationTest {
    @Parameterized.Parameter
    public String browserName;

    private WebDriver driver;
    private User user;

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Collection<Object[]> browsers() {
        return Arrays.asList(new Object[][] {
                {"chrome"},
                {"yandex"}
        });
    }

    @Before
    public void setUp() {
        driver = WebDriverFactory.getWebDriver(browserName);
        user = new User("testtest-mix@yandex.ru", "qwerty12345", "Kuvalda");
    }

    @Test
    public void mainPageLoginUserTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.loginUserWithLoginButton(user.getEmail(), user.getPassword());
        Assert.assertTrue(mainPage.isMakeAnOrderButtonVisible());
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://stellarburgers.nomoreparties.site/";
        Assert.assertEquals("Должен быть переход на главную страницу", expectedUrl, currentUrl);
    }

    @Test
    public void profilePageLoginUserTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.loginUserWithPersonalAccountButton(user.getEmail(), user.getPassword());
        Assert.assertTrue(mainPage.isMakeAnOrderButtonVisible());
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://stellarburgers.nomoreparties.site/";
        Assert.assertEquals("Должен быть переход на главную страницу", expectedUrl, currentUrl);
    }

    @Test
    public void registrationFormLoginUserTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        MainPage mainPage = new MainPage(driver);

        registerPage.loginUserWithLoginButton(user.getEmail(), user.getPassword());
        Assert.assertTrue(mainPage.isMakeAnOrderButtonVisible());
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://stellarburgers.nomoreparties.site/";
        Assert.assertEquals("Должен быть переход на главную страницу", expectedUrl, currentUrl);
    }

    @Test
    public void forgotPasswordPageLoginUserTest() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        MainPage mainPage = new MainPage(driver);

        forgotPasswordPage.loginUserWithLoginButton(user.getEmail(), user.getPassword());
        Assert.assertTrue(mainPage.isMakeAnOrderButtonVisible());
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://stellarburgers.nomoreparties.site/";
        Assert.assertEquals("Должен быть переход на главную страницу", expectedUrl, currentUrl);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

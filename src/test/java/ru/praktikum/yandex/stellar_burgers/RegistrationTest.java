package ru.praktikum.yandex.stellar_burgers;

import config.WebDriverFactory;
import io.restassured.response.Response;
import model.User;
import model.UserCreateResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object.RegisterPage;
import ru.praktikum.yandex.stellar_burgers.steps.UserSteps;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RegistrationTest {
    @Parameterized.Parameter
    public String browserName;

    private WebDriver driver;
    private UserCreateResponse userCreateResponse;
    private UserSteps userSteps;
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
        user = new User("obrazecTest8@yandex.ru", "qwerty12345", "obrazecTest8");
        userSteps = new UserSteps();
        driver = WebDriverFactory.getWebDriver(browserName);
    }

    @Test
    public void checkSignUpUserTest() {
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.signUpUser(user.getName(), user.getEmail(), user.getPassword());
        String expectedUrl = "https://stellarburgers.nomoreparties.site/login";
        boolean element = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Должен быть переход на страницу входа", expectedUrl, currentUrl);
        Assert.assertTrue(registerPage.isLoginFormVisible());
    }

    @Test
    public void checkFailedSignUpUserTest() {
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.signUpUser(user.getName(), user.getEmail(), "12345");
        Assert.assertTrue(registerPage.isIncorrectPasswordErrorVisible());
    }

    @After
    public void tearDown() {
        Response response = userSteps.loginUser(user);
        userCreateResponse = response.as(UserCreateResponse.class);
        if (userCreateResponse != null && userCreateResponse.getAccessToken() != null) {
            userSteps.deleteUser(userCreateResponse.getAccessToken());
        }
        driver.quit();
    }
}

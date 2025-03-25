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
import page_object.LoginPage;
import page_object.MainPage;
import page_object.PersonalAccountPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PersonalAccountTest {
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
        user = new User("testtest-mix@yandex.ru", "qwerty12345", "Kuvalda");
        driver = WebDriverFactory.getWebDriver(browserName);
    }

    @Test
    public void movingIntoProfileTest() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        personalAccountPage.movingIntoPersonalAccount(user.getEmail(), user.getPassword());
        Assert.assertTrue(personalAccountPage.isProfileButtonVisible());
    }

    @Test
    public void movingIntoConstructorTest() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        MainPage mainPage = new MainPage(driver);

        personalAccountPage.movingIntoPersonalAccount(user.getEmail(), user.getPassword());
        personalAccountPage.movingIntoConstructor();
        Assert.assertTrue(mainPage.isMakeAnOrderButtonVisible());
    }

    @Test
    public void movingByMainLogoTest() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        MainPage mainPage = new MainPage(driver);

        personalAccountPage.movingIntoPersonalAccount(user.getEmail(), user.getPassword());
        personalAccountPage.movingByMainLogo();
        Assert.assertTrue(mainPage.isMakeAnOrderButtonVisible());
    }

    @Test
    public void exitFromAccountTest() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        personalAccountPage.movingIntoPersonalAccount(user.getEmail(), user.getPassword());
        personalAccountPage.exitFromProfile();
        Assert.assertTrue(loginPage.isLoginFieldVisible());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

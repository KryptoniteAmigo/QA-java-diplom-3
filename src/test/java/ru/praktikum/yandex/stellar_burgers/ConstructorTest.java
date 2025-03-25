package ru.praktikum.yandex.stellar_burgers;

import config.WebDriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page_object.MainPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ConstructorTest {
    private WebDriver driver;

    @Parameterized.Parameter
    public String browserName;

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
    }

    @Test
    public void checkBunsTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickFillingButton(); //добавил, так как по дефолту уже включена вкладка "Булки" и кнопка некликабельна
        mainPage.clickBunsButton(); //и вот здесь ее уже можно нажать

        boolean isVisible = mainPage.isElementInViewport(mainPage.getBunsItem());
        Assert.assertTrue("Первый элемент из раздела 'Булки' не отображается целиком", isVisible);
    }

    @Test
    public void checkSaucesTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSauceButton();

        boolean isVisible = mainPage.isElementInViewport(mainPage.getSauceItem());
        Assert.assertTrue("Первый элемент из раздела 'Соусы' не отображается целиком", isVisible);
    }

    @Test
    public void checkFillingsTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickFillingButton();

        boolean isVisible = mainPage.isElementInViewport(mainPage.getFillingsItem());
        Assert.assertTrue("Первый элемент из раздела 'Начинки' не отображается целиком", isVisible);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

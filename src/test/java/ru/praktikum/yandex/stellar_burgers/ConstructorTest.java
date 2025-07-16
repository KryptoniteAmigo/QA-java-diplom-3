package ru.praktikum.yandex.stellar_burgers;

import config.WebDriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Dimension;
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
        driver.manage().window().setSize(new Dimension(1500, 1350));
    }

    @Test
    public void checkBunsTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickFillingButton();
        mainPage.clickBunsButton();
        mainPage.pause(2000);

        boolean isVisibleSauceItem = mainPage.isElementInViewport(mainPage.getSauceItem());
        boolean isDisplayedTab = mainPage.isElementDisplayed(mainPage.getBunsTabSelected());
        Assert.assertTrue("Не сработал переход на вкладку 'Булки'", isDisplayedTab);
        Assert.assertTrue("Элемент из раздела 'Соусы' не отобразился во вкладке 'Булки'", isVisibleSauceItem);
    }

    @Test
    public void checkSaucesTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSauceButton();
        mainPage.pause(2000);

        boolean isVisibleFillingItem = mainPage.isElementInViewport(mainPage.getFillingsItem());
        boolean isDisplayedTab = mainPage.isElementDisplayed(mainPage.getSauceTabSelected());
        Assert.assertTrue("Не сработал переход на вкладку 'Соусы'", isDisplayedTab);
        //проверяю, что начинки отображаются на экране после клика по вкладке с соусами (на вкладке с булками их видно не будет)
        Assert.assertTrue("Элемент из раздела 'Начинки' не отобразился во вкладке 'Соусы'", isVisibleFillingItem);
    }

    @Test
    public void checkFillingsTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickFillingButton();
        mainPage.pause(2000);

        boolean isVisibleSauceItem = mainPage.isElementInViewport(mainPage.getSauceItem());
        boolean isDisplayedTab = mainPage.isElementDisplayed(mainPage.getFillingTabSelected());
        Assert.assertTrue("Не сработал переход на вкладку 'Начинки'", isDisplayedTab);
        Assert.assertFalse("Элемент из раздела 'Соусы' не должен отображаться во вкладке 'Начинки'", isVisibleSauceItem);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

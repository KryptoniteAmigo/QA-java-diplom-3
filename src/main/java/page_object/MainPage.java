package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private LoginPage loginPage;
    private final String url = "https://stellarburgers.nomoreparties.site/";

    private final By loginButton = By.xpath(".//button[contains(@class,'button_button__33qZ0')]");
    private final By personalAccountButton = By.xpath(".//p[contains(@class,'AppHeader_header__linkText')and text()='Личный Кабинет']");
    private final By mainLogoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By constructorButton = By.xpath(".//p[contains(@class,'AppHeader_header__linkText')and text()='Конструктор']");
    private final By bunsButton = By.xpath(".//span[contains(@class,'text text_type_main-default')and text()='Булки']");
    private final By bunsTabSelected = By.xpath(".//div[contains(@class, 'tab_tab_type_current') and .//span[text()='Булки']]");
    private final By sauceButton = By.xpath(".//span[contains(@class,'text text_type_main-default')and text()='Соусы']");
    private final By sauceTabSelected = By.xpath(".//div[contains(@class, 'tab_tab_type_current') and .//span[text()='Соусы']]");
    private final By fillingsButton = By.xpath(".//span[contains(@class,'text text_type_main-default')and text()='Начинки']");
    private final By fillingTabSelected = By.xpath(".//div[contains(@class, 'tab_tab_type_current') and .//span[text()='Начинки']]");
    private final By makeAnOrderButton = By.xpath(".//button[contains(@class,'button_button__33qZ0')and text()='Оформить заказ']");
    private final By firstBunItem = By.xpath(".//img[@alt='Флюоресцентная булка R2-D3']");
    private final By firstSauceItem = By.xpath(".//img[@alt='Соус Spicy-X']");
    private final By firstFillingItem = By.xpath(".//img[@alt='Мясо бессмертных моллюсков Protostomia']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем страницу главную страницу с конструктором")
    public void open() {
        driver.get(url);
    }

    @Step("Нажимаем на кнопк 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажимаем на кнопку 'Личный Кабинет'")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажимаем на кнопку 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажимаем на кнопку 'Булки'")
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    @Step("Нажимаем на кнопку 'Соусы'")
    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    @Step("Нажимаем на кнопку 'Начинки'")
    public void clickFillingButton() {
        driver.findElement(fillingsButton).click();
    }

    @Step("Нажимаем на логотип 'Stellar Burgers'")
    public void clickMainLogoButton() {
        driver.findElement(mainLogoButton).click();
    }

    public By getBunsItem() {
        return firstBunItem;
    }

    public By getSauceItem() {
        return firstSauceItem;
    }

    public By getFillingsItem() {
        return firstFillingItem;
    }

    public By getBunsTabSelected() {
        return bunsTabSelected;
    }

    public By getSauceTabSelected() {
        return sauceTabSelected;
    }

    public By getFillingTabSelected() {
        return fillingTabSelected;
    }

    @Step("Проверяем, что кнопка 'Оформить заказ' отображается")
    public boolean isMakeAnOrderButtonVisible(){
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(makeAnOrderButton));
        return driver.findElement(makeAnOrderButton).isDisplayed();
    }

    @Step("Проверяем, что элемент отображается на странице")
    public boolean isElementInViewport(By locator) {
        WebElement element = driver.findElement(locator);

        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        Rectangle rect = element.getRect();
        Dimension windowSize = driver.manage().window().getSize();

        boolean withinX = rect.getX() >= 0 && (rect.getX() + rect.getWidth()) <= windowSize.getWidth();
        boolean withinY = rect.getY() >= 0 && (rect.getY() + rect.getHeight()) <= windowSize.getHeight();

        return withinX && withinY;
    }

    @Step("Проверяем наличие элемента на странице")
    public boolean isElementDisplayed(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    @Step("Авторизуемся через кнопку 'Вход'")
    public void loginUserWithLoginButton(String email, String password){
        open();
        clickLoginButton();
        loginPage = new LoginPage(driver);
        loginPage.loginUser(email, password);
    }

    @Step("Авторизуемся через кнопку 'Личный аккаунт'")
    public void loginUserWithPersonalAccountButton(String email, String password){
        open();
        clickPersonalAccountButton();
        loginPage = new LoginPage(driver);
        loginPage.loginUser(email, password);
    }

    @Step("Делаем явную паузу")
    public void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }




}

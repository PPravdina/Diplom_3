package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    public static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";
    private final WebDriver webDriver;
    private final By personalAccountLink = By.xpath(".//a[@href='/account']");
    private final By enterAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By checkoutButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By bunTab = By.cssSelector(".BurgerIngredients_ingredients__1N8v2 > div:nth-child(2) > div:nth-child(1)");
    private final By sauceTab = By.cssSelector(".BurgerIngredients_ingredients__1N8v2 > div:nth-child(2) > div:nth-child(2)");
    private final By fillingsTab = By.cssSelector(".BurgerIngredients_ingredients__1N8v2 > div:nth-child(2) > div.tab_tab__1SPyG.tab_tab_type_current__2BEPc");


    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть главную страницу")
    public void open() {
        webDriver.get(MAIN_URL);
    }

    @Step("Нажать Личный кабинет")
    public void pressPersonalAccountLink() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement personalAccountElement = wait.until(ExpectedConditions.elementToBeClickable(personalAccountLink));
        personalAccountElement.click();
    }

    @Step("Нажать Войти в аккаунт")
    public void pressEnterAccountButton() {
        webDriver.findElement(enterAccountButton).click();
    }

    @Step("Проверить, что кнопка Оформить заказ для авторизованного пользователя появилась")
    public boolean isCheckOutButtonPresent() {
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(checkoutButton));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Нажать Булки")
    public void pressBunTab() {
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        WebElement bunTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(bunTab));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(bunTab)).click().perform();
    }

    @Step("Нажать Соусы")
    public void pressSauceTab() {
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        WebElement sauceTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sauceTab));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(sauceTab)).click().perform();
    }

    @Step("Нажать Начинки")
    public void pressFillingsTab() {
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        WebElement fillingsTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTab));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(fillingsTab)).click().perform();
    }

    @Step("Найти элемент Булки")
    public By getBunTab() {
        return bunTab;
    }

    @Step("Найти элемент Соусы")
    public By getSauceTab() {
        return sauceTab;
    }

    @Step("Найти элемент Начинки")
    public By getFillingsTab() {
        return fillingsTab;
    }
}

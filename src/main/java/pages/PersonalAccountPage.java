package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {
    private final WebDriver webDriver;
    private final By profileButton = By.xpath(".//a[@href='/account/profile']");
    private final By iconButton = By.xpath(".//a[@href='/']");
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final By constructorButton = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2']");

    public PersonalAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Нажать Выйти")
    public void pressExitButton() {
        webDriver.findElement(exitButton).click();
    }

    @Step("Проверить, что кнопка Профиль появилась на странице")
    public boolean isProfileButtonPresent() {
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(profileButton));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Нажать на Конструктор")
    public void pressConstructorButton() {
        webDriver.findElement(constructorButton).click();
    }

    @Step("Нажать на Логотип")
    public void pressIconButton() {
        webDriver.findElement(iconButton).click();
    }
}

package pages;

import api.model.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    public static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";
    private final WebDriver webDriver;
    private final By emailInput = By.xpath(".//input[@name='name']");
    private final By passwordInput = By.xpath(".//input[@name='Пароль']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть страницу авторизации")
    public LoginPage open() {
        webDriver.get(LOGIN_URL);
        return this;
    }

    @Step("Ввести данные")
    public void fillOutLoginForm(User user) {
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        // Сделать поле активным (в фокусе)
        webDriver.findElement(emailInput).click();
        // Ввести значение
        webDriver.findElement(emailInput).sendKeys(user.getEmail());
        // Повторить для других полей
        webDriver.findElement(passwordInput).click();
        webDriver.findElement(passwordInput).sendKeys(user.getPassword());
    }

    @Step("Нажать Войти")
    public void pressLoginButton() {
        webDriver.findElement(loginButton).click();
    }

    @Step("Проверить, открылась ли страница авторизации")
    public boolean isLoginPresent() {
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}

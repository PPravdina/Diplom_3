package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgetPasswordPage {
    public static final String FORGOT_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    private final WebDriver webDriver;
    private final By loginButton = By.xpath(".//a[@href='/login']");

    public ForgetPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть страницу Забыли пароль")
    public ForgetPasswordPage open() {
        webDriver.get(FORGOT_URL);
        return this;
    }

    @Step("Нажать Войти")
    public void pressLoginButton() {
        webDriver.findElement(loginButton).click();
    }
}

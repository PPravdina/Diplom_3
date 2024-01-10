package pages;

import api.model.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    public static final String REGISTER_URL = "https://stellarburgers.nomoreparties.site/register";

    private final WebDriver webDriver;
    //Блок вопросов о важном
    private final By nameInput = By.xpath(".//input[@class='text input__textfield text_type_main-default']");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input[@class='text input__textfield text_type_main-default']");
    private final By passwordInput = By.xpath(".//input[@name='Пароль']");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By nextPageOpen = By.xpath(".//div[@class='Auth_login__3hAey']//h2[text()='Вход']");
    private final By wrongPasswordMessage = By.xpath(".//p[text()='Некорректный пароль']");
    private final By loginPageButton = By.xpath(".//a[@href='/login']");


    public RegisterPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть страницу регистрации")
    public RegisterPage open() {
        webDriver.get(REGISTER_URL);
        return this;
    }

    @Step("Перейти на страницу авторизации")
    public void openLoginPage() {
        webDriver.findElement(loginPageButton).click();
    }

    @Step("Ввести данные")
    public void fillOutForm(User user) {
        // Сделать поле активным (в фокусе)
        webDriver.findElement(nameInput).click();
        // Ввести значение
        webDriver.findElement(nameInput).sendKeys(user.getName());

        // Повторить для других полей
        webDriver.findElement(emailInput).click();
        webDriver.findElement(emailInput).sendKeys(user.getEmail());

        webDriver.findElement(passwordInput).click();
        webDriver.findElement(passwordInput).sendKeys(user.getPassword());
    }


    @Step("Нажать Зарегистироваться")
    public void pressRegisterButton() {
        webDriver.findElement(registerButton).click();
    }

    @Step("Проверить, открылась ли следующая страница")
    public boolean isNextPageDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(nextPageOpen));
            return true; // Если элемент найден, возвращаем true
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            return false; // Если элемент не найден в течение заданного времени, возвращаем false
        }
    }

    @Step("Проверить наличие сообщения об ошибке")
    public boolean isMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(wrongPasswordMessage));
            return true; // Если элемент найден, возвращаем true
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            return false; // Если элемент не найден в течение заданного времени, возвращаем false
        }
    }
}

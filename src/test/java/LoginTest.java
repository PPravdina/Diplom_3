import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginTest extends BaseTest {

    public LoginTest(String driverPath) {
        super(driverPath);
    }

    @Override
    @Before
    public void setUp() {
        lengthPassword = 8;
        super.setUp();
        userClient.registerUser();
    }

    @Test
    @DisplayName("Авторизация пользователя по кнопке Войти в аккаунт на главной странице")
    @Description("Тест для входа с использованием кнопки 'Войти в аккаунт' на главной странице")
    public void loginUsingMainPageButtonTest() {
        mainPage.open();
        mainPage.pressEnterAccountButton();
        loginPage.fillOutLoginForm(user);
        loginPage.pressLoginButton();
        //Проверяем, что главная страница открылась
        Assert.assertTrue("Авторизация не прошла", mainPage.isCheckOutButtonPresent());
    }

    @Test
    @DisplayName("Авторизация пользователя по кнопке Личный кабинет на главной странице")
    @Description("Тест для входа с использованием кнопки 'Личный кабинет' на главной странице")
    public void loginUsingPersonalAccountButtonTest() {
        mainPage.open();
        mainPage.pressPersonalAccountLink();
        loginPage.fillOutLoginForm(user);
        loginPage.pressLoginButton();
        //Проверяем, что главная страница открылась
        Assert.assertTrue("Авторизация не прошла", mainPage.isCheckOutButtonPresent());
    }

    @Test
    @DisplayName("Авторизация пользователя по кнопке Войти на странице регистрации")
    @Description("Тест для входа с использованием кнопки в форме регистрации")
    public void loginUsingRegistrationFormButtonTest() {
        registerPage.open();
        registerPage.openLoginPage();
        loginPage.fillOutLoginForm(user);
        loginPage.pressLoginButton();
        //Проверяем, что главная страница открылась
        Assert.assertTrue("Авторизация не прошла", mainPage.isCheckOutButtonPresent());
    }

    @Test
    @DisplayName("Авторизация пользователя по кнопке Войти на странице восстановления пароля")
    @Description("Тест для входа с использованием кнопки в форме восстановления пароля")
    public void loginUsingForgotPasswordButtonTest() {
        forgetPasswordPage.open();
        forgetPasswordPage.pressLoginButton();
        loginPage.fillOutLoginForm(user);
        loginPage.pressLoginButton();
        //Проверяем, что главная страница открылась
        Assert.assertTrue("Авторизация не прошла", mainPage.isCheckOutButtonPresent());
    }
}
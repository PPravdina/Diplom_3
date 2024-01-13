import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonalAccountTest extends BaseTest {

    @Override
    @Before
    public void setUp() {
        lengthPassword = 8;
        super.setUp();
        userClient.registerUser();
        mainPage.open();
    }

    @Test
    @DisplayName("Открыть Личный аккаунт без авторизации пользователя")
    @Description("Тест для открытия Личного аккаунта на главной странице без авторизации")
    public void PersonalAccountWithoutLoginTest() {
        mainPage.pressPersonalAccountLink();
        //Проверяем, что главная страница открылась
        Assert.assertTrue("Страница авторизации не открылась", loginPage.isLoginPresent());
    }

    @Test
    @DisplayName("Открыть Личный аккаунт авторизованного пользователя")
    @Description("Тест для открытия Личного аккаунта на главной странице с авторизации")
    public void PersonalAccountWithLoginTest() {
        mainPage.pressPersonalAccountLink();
        loginPage.fillOutLoginForm(user);
        loginPage.pressLoginButton();
        mainPage.pressPersonalAccountLink();
        //Проверяем, что главная страница открылась
        Assert.assertTrue("Страница личного кабинета не открылась", personalAccountPage.isProfileButtonPresent());
    }

    @Test
    @DisplayName("Открыть конструктор из личного аккаунта")
    @Description("Тестируется открытие конструктора из личного кабинета")
    public void pressConstructorTest() {
        mainPage.pressPersonalAccountLink();
        loginPage.fillOutLoginForm(user);
        loginPage.pressLoginButton();
        mainPage.pressPersonalAccountLink();
        personalAccountPage.pressConstructorButton();
        //Проверяем, что главная страница открылась
        Assert.assertTrue("Страница конструктора не открылась", mainPage.isCheckOutButtonPresent());
    }

    @Test
    @DisplayName("Открыть конструктор из личного аккаунта по клику на логотип")
    @Description("Тестируется открытие конструктора из личного кабинета по клику на логотип")
    public void pressIconConstructorTest() {
        mainPage.pressPersonalAccountLink();
        loginPage.fillOutLoginForm(user);
        loginPage.pressLoginButton();
        mainPage.pressPersonalAccountLink();
        personalAccountPage.pressIconButton();
        //Проверяем, что главная страница открылась
        Assert.assertTrue("Страница конструктора не открылась", mainPage.isCheckOutButtonPresent());
    }
}

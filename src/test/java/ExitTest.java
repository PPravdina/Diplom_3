import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExitTest extends BaseTest {

    @Override
    @Before
    public void setUp() {
        lengthPassword = 8;
        super.setUp();
        userClient.registerUser();
        mainPage.open();
        mainPage.pressPersonalAccountLink();
        loginPage.fillOutLoginForm(user);
        loginPage.pressLoginButton();
        mainPage.pressPersonalAccountLink();
    }

    @Test
    @DisplayName("Проверяем возможность выйти из аккаунта")
    @Description("Тестируется разлогирование пользователя в личном аккаунте")
    public void pressExitTest() {
        personalAccountPage.pressExitButton();
        //Проверяем, что главная страница открылась
        Assert.assertTrue("Выйти из аккаунта не получилось", loginPage.isLoginPresent());
    }
}

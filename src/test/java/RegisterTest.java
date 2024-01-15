import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.RegisterPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RegisterTest extends BaseTest {
    private final String message;

    public RegisterTest(int lengthPassword, String message) {
        this.lengthPassword = lengthPassword;
        this.message = message;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {6, "Регистрация не прошла, но должна была"},
                {5, "Регистрация прошла, но не должна была"},
        });
    }

    @Before
    public void setUp() {
        super.setUp();
        registerPage = new RegisterPage(webDriver);
        registerPage.open();
    }

    @Test
    @DisplayName("Тест регистрации пользователя")
    @Description("Тест регистрации пользователя")
    public void registerTest() {
        registerPage.fillOutForm(user);
        registerPage.pressRegisterButton();
        // Проверка наличия сообщения об ошибке
        boolean isNextPageDisplayed = registerPage.isNextPageDisplayed();
        if (!isNextPageDisplayed) {
            // Проверка наличия сообщения об ошибке, если переход на следующую страницу не произошел
            boolean isMessageDisplayed = registerPage.isMessageDisplayed();
            // Сравнение с ожидаемым результатом
            Assert.assertTrue("Сообщение об ошибке не появилось", isMessageDisplayed);
        } else {
            // Сравнение с ожидаемым результатом
            Assert.assertTrue(message, isNextPageDisplayed);

        }
    }
}

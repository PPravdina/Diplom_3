import api.client.UserClient;
import api.model.User;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import util.TestDataGenerator;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
//Не придумала, как прогнать без параметризации тут, из-за этого есть "поломанный" тест
public class BaseTest {
    protected final String driverPath;
    protected WebDriver webDriver;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected ForgetPasswordPage forgetPasswordPage;
    protected PersonalAccountPage personalAccountPage;
    protected TestDataGenerator testDataGenerator;
    protected User user;
    protected UserClient userClient;
    protected int lengthPassword;

    public BaseTest(String driverPath) {
        this.driverPath = driverPath;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {ConfProperties.getProperty("chromedriver")},
                {ConfProperties.getProperty("yandexdriver")},
        });
    }

    @Before
    public void setUp() {
        testDataGenerator = new TestDataGenerator();
        user = TestDataGenerator.generateRandomUser(lengthPassword);
        userClient = new UserClient(user);
        System.setProperty("webdriver.chrome.driver", driverPath);
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(3, java.util.concurrent.TimeUnit.SECONDS);
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        registerPage = new RegisterPage(webDriver);
        forgetPasswordPage = new ForgetPasswordPage(webDriver);
        personalAccountPage = new PersonalAccountPage(webDriver);
    }

    @After
    public void tearDown() {
        webDriver.quit();
        // Проверка длины пароля
        if (user.getPassword() != null && user.getPassword().length() > 5) {
            // Авторизация пользователя
            Response loginResponse = userClient.loginUser();

            // Получение access token
            String accessToken = loginResponse.jsonPath().getString("accessToken");
            user.setAccessToken(accessToken);

            // Удаление пользователя
            Response response = userClient.deleteUser();
            response
                    .then()
                    .log().all()
                    .statusCode(202)
                    .body("success", equalTo(true))
                    .and()
                    .body("message", equalTo("User successfully removed"));
        }
    }
}

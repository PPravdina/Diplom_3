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

import static org.hamcrest.CoreMatchers.equalTo;

public class BaseTest {
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


    @Before
    public void setUp() {
        testDataGenerator = new TestDataGenerator();
        user = TestDataGenerator.generateRandomUser(lengthPassword);
        userClient = new UserClient(user);

        // Получение пути к драйверу из системного свойства Maven
        String driverName = "browser.driver.path";
        String driverPath = System.getProperty(driverName);

        if (driverPath != null) {
            System.setProperty("webdriver.chrome.driver", driverPath);
        } else {
            System.out.println("Путь к драйверу не указан. Используется драйвер по умолчанию.");
        }

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

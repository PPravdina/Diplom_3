import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConstructorTest extends BaseTest {
    public ConstructorTest(String driverPath) {
        super(driverPath);
    }

    @Override
    @Before
    public void setUp() {
        lengthPassword = 0;
        super.setUp();
        mainPage.open();
    }

    @Test
    @DisplayName("Нажать на Булки в конструкторе и проверить переход к ним")
    @Description("Тестируется переход к булкам в конструкторе")
    public void pressBunTabTest() {
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        WebElement bunTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getBunTab()));
        mainPage.pressBunTab();
        // Получим значение атрибута class
        String classAttributeValue = bunTabElement.getAttribute("class");
        // Проверим, что значение атрибута содержит ожидаемый класс
        Assert.assertTrue("Класс не содержит ожидаемый класс", classAttributeValue.contains("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Нажать на Соусы в конструкторе и проверить переход к ним")
    @Description("Тестируется переход к соусам в конструкторе")
    public void pressSauceTabTest() {
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        WebElement sauceTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getSauceTab()));
        mainPage.pressSauceTab();
        // Получим значение атрибута class
        String classAttributeValue = sauceTabElement.getAttribute("class");
        // Проверим, что значение атрибута содержит ожидаемый класс
        Assert.assertTrue("Класс не содержит ожидаемый класс", classAttributeValue.contains("tab_tab_type_current__2BEPc"));
    }

    @Test
    @DisplayName("Нажать на Начинки в конструкторе и проверить переход к ним")
    @Description("Тестируется переход к начинкам в конструкторе")
    public void pressFillingsTabTest() {
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        WebElement fillingsTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(mainPage.getFillingsTab()));
        mainPage.pressFillingsTab();
        // Получим значение атрибута class
        String classAttributeValue = fillingsTabElement.getAttribute("class");
        // Проверим, что значение атрибута содержит ожидаемый класс
        Assert.assertTrue("Класс не содержит ожидаемый класс", classAttributeValue.contains("tab_tab_type_current__2BEPc"));
    }
}

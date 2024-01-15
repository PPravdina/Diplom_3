package util;

import api.model.User;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;

public class TestDataGenerator {

    private static final Faker faker = new Faker();

    @Step("Генерация рандомного пользователя")
    public static User generateRandomUser(int lengthPassword) {
        User user = new User();
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(lengthPassword, lengthPassword + 2)); // Указываем минимальную и максимальную длину пароля
        user.setName(faker.name().firstName());

        return user;
    }
}

package util;

import api.model.User;
import io.qameta.allure.Step;

import java.util.Random;

public class TestDataGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    //Генерация рандомных текстовых данных
    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            randomString.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return randomString.toString();
    }

    @Step("Генерация рандомного пользователя")
    public static User generateRandomUser(int lengthPassword) {
        User user = new User();
        user.setEmail(generateRandomString(8).toLowerCase() + "@example.com");
        user.setPassword(generateRandomString(lengthPassword));
        user.setName(generateRandomString(8));

        return user;
    }
}
package api.client;

import api.model.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserClient extends BaseClient {
    private static final String USER_PATH = "/api/auth/";
    private final User user;

    public UserClient(User user) {
        this.user = user;
    }

    @Step("Зарегистрировать пользователя")
    public Response registerUser() {
        String requestBody = "{ \"email\": \"" + user.getEmail() + "\", \"password\": \"" + user.getPassword() + "\", \"name\": \"" + user.getName() + "\" }";

        return getRequestSpecification()
                .and()
                .body(requestBody)
                .when()
                .post(USER_PATH + "register");
    }

    @Step("Авторизоваться пользователем")
    public Response loginUser() {
        return getRequestSpecification()
                .and()
                .body(user)
                .when()
                .post(USER_PATH + "login");
    }

    @Step("Удалить пользователя")
    public Response deleteUser() {
        return getRequestSpecification()
                .header("Authorization", user.getAccessToken())
                .delete(USER_PATH + "user");
    }
}
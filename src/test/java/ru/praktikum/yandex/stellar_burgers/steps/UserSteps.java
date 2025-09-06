package ru.praktikum.yandex.stellar_burgers.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import client.UserClient;
import model.User;

public class UserSteps {
    private final UserClient userClient = new UserClient();

    @Step("Авторизация пользователя")
    public Response loginUser(User user) {
        return userClient.loginUser(user);
    }

    @Step("Удалить пользователя")
    public void deleteUser(String accessToken) {
        userClient.deleteUser(accessToken);
    }
}

package client;

import com.google.gson.Gson;
import io.restassured.response.Response;
import config.ApiSpec;
import model.User;

import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String USER_PATH = "/api/auth/user";
    private static final String LOGIN_PATH = "/api/auth/login";

    private final Gson gson = new Gson();

    public Response loginUser(User user) {
        String json = gson.toJson(user);
        return given()
                .spec(ApiSpec.getBaseSpec())
                .body(json)
                .when()
                .post(LOGIN_PATH);
    }

    public void deleteUser(String accessToken) {
        given()
                .spec(ApiSpec.getBaseSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(USER_PATH);
    }
}

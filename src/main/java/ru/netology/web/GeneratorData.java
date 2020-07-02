package ru.netology.web;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class GeneratorData {

    private static RequestSpecification request = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    static void RegUser(RegistrationDto regDto) {
        given()
                .spec(request)
                .body(regDto)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static RegistrationDto genValidActiveUser() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationDto validUser = new RegistrationDto(
                faker.name().username().toLowerCase(),
                faker.internet().password(),
                "active"
        );
        RegUser(validUser);
        return validUser;
    }

    public static RegistrationDto genValidBlockedUser() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationDto blockedUser = new RegistrationDto(
                faker.name().username().toLowerCase(),
                faker.internet().password(),
                "blocked"
        );
        RegUser(blockedUser);
        return blockedUser;
    }

    public static RegistrationDto genInvalidLogin() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationDto invalidLogin = new RegistrationDto(
                "username",
                faker.internet().password(),
                "active"
        );
        RegUser(invalidLogin);
        return invalidLogin;
    }

    public static RegistrationDto genBadPassword() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationDto badPassword = new RegistrationDto(
                faker.name().username().toLowerCase(),
                "badpassword",
                "active"
        );
        RegUser(badPassword);
        return badPassword;
    }


}

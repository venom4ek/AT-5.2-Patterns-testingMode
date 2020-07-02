package ru.netology.web;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationTest {

    @Test
    void shouldSubmitWhenValidUser() {
        RegistrationDto user = GeneratorData.genValidActiveUser();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login]").click();
        $(byText("Личный кабинет")).waitUntil(visible, 5000);
    }

    @Test
    void shouldSubmitWhenBlockedUser() {
        RegistrationDto user = GeneratorData.genValidBlockedUser();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login]").click();
        $(byText("Пользователь заблокирован")).waitUntil(visible, 5000);
    }

    @Test
    void shouldSubmitWhenInvalidUser() {
        RegistrationDto user = GeneratorData.genInvalidLogin();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys("nameuser");
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login]").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(visible, 5000);
    }

    @Test
    void shouldSubmitWhenBadPassword() {
        RegistrationDto user = GeneratorData.genBadPassword();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys("jrjkdfy387yu");
        $("[data-test-id=action-login]").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(visible, 5000);
    }
}
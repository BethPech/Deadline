package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.getAnotherAuthInfo;
import static ru.netology.data.DataHelper.getAuthInfo;
import static ru.netology.data.DBHelper.*;

public class VerificationCodeTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterEach
    void tidyUp() {
        wipeCodes();
    }

    @AfterAll
    static void totalTidyUp() {
        wipeEverything();
    }

    @Test
    void shouldLogIn() {
        val verificationPage = new LoginPage().validLogin(getAuthInfo());
        verificationPage.validVerify(getCode());
    }


    @Test
    void shouldBeBlocked() {
        LoginPage page = new LoginPage();
        page.login(getAnotherAuthInfo());
        page.login(getAnotherAuthInfo());
        page.blockingLogin(getAnotherAuthInfo());
    }
}
package ru.netology.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.page.BuyPage;
import ru.netology.page.CreditPage;
import ru.netology.page.MainPage;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.CardGenerator.*;
import static ru.netology.data.CardGenerator.cvc1;
import static ru.netology.data.SQLHelper.checkDataExists;

public class DataBaseTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("http://localhost:8080/");


    }

    @BeforeEach
    void setUp() {
        Selenide.refresh();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    void checkDataExistInPaymentEntity() throws SQLException {
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        buy.success();
        String startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        String nextMinute = LocalDateTime.now().plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        boolean dataExists = checkDataExists(startTime, nextMinute, "SELECT * FROM payment_entity WHERE created BETWEEN ? AND ?");
        assertTrue(dataExists);
    }

    @Test
    void checkDataExistInCreditRequestEntity() throws SQLException {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendForm(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        credit.success();
        String startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        String nextMinute = LocalDateTime.now().plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        boolean dataExists = checkDataExists(startTime, nextMinute, "SELECT * FROM credit_request_entity WHERE created BETWEEN ? AND ?");
        assertTrue(dataExists);
    }

    @Test
    void tempCheckDataExistInPaymentEntity() throws SQLException {
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        buy.success();
        String startTime = LocalDateTime.now().minusHours(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        String nextMinute = LocalDateTime.now().minusHours(5).plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        boolean dataExists = checkDataExists(startTime, nextMinute, "SELECT * FROM payment_entity WHERE created BETWEEN ? AND ?");
        assertTrue(dataExists);
    }

}



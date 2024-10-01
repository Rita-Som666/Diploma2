package ru.netology.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.page.CreditPage;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.CardGenerator.*;
import static ru.netology.data.SQLHelper.cleaner;

public class AqaShopTestPayToCredit {
    @BeforeAll
    static void setUpAll() {
        cleaner();
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

    public void successBuy() {


        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendForm(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        credit.success();

    }

    @Test
    void sendWithInvalidCardNumber() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendForm(generateData(cardNumber(invalidCardNumber1()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        credit.error();
    }

    @Test
    void sendWithNumberUnknownToTheDatabase() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendForm(generateData(cardNumber(invalidCardNumber2()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        credit.error();
    }

    @Test
    void sendWithMonthMore12() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendForm(generateData(cardNumber(validCardNumber()), month(invalidMonth()), 1, name(randomName()), cvv(cvc1())));
        credit.invalidValidityPeriod();
    }

    @Test
    void sendWithOneFigureMonth() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendForm(generateData(cardNumber(validCardNumber()), invalidFormatMonth(1), 1, name(randomName()), cvv(cvc1())));
        credit.invalidFormat();
    }

    @Test
    void sendWithYearLessCurrent() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(1)), -1, name(randomName()), cvv(cvc1())));
        credit.expired();

    }

    @Test
    void sendWithMonthLessCurrentAndCurrentYear() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(-1)), 0, name(randomName()), cvv(cvc1())));
        credit.expired();
    }

    @Test
    void sendWithYearMorePlus5() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(1)), 6, name(randomName()), cvv(cvc1())));
        credit.invalidValidityPeriod();
    }

    @Test
    void sendWithCyrillicName() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(1)), 1, name(rusName()), cvv(cvc1())));
        credit.invalidFormat();
    }

    @Test
    void sendWithDigitInName() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(1)), 1, name(nameWitsDigit()), cvv(cvc1())));
        credit.invalidFormat();
    }

    @Test
    void sendWithSymbolInName() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(1)), 1, name(naneWithSymbol()), cvv(cvc1())));
        credit.invalidFormat();
    }

    @Test
    void sendWithTwoDigitsInCvv() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(1)), 1, name(randomName()), cvv(invalidCvc())));
        credit.invalidFormat();
    }

    @Test
    void sendWithOutNumber() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendFormWithOutNumber(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        credit.blankField();
    }

    @Test
    void sendWithOutMonth() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendFormWithOutMonth(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        credit.blankField();
    }

    @Test
    void sendWithOutYear() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendFormWithOutYear(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        credit.blankField();
    }

    @Test
    void sendWithOutName() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendFormWithOutName(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        credit.blankField();
    }

    @Test
    void sendWithOutCvv() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendFormWithOutCvv(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        credit.blankField();
    }

    @Test
    void sendBlankForm() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.sendBlankForm();
        credit.blankFields();
    }
}

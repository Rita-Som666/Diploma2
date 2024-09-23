package ru.netology.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.BuyPage;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.CardGenerator.*;



public class AqaShopTestPayToCard {


    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("http://localhost:8080/");



    }

    @BeforeEach
    void setUp(){
        Selenide.refresh();
    }

    @AfterAll
    static void tearDownAll(){
        SelenideLogger.removeListener("allure");
    }

    @Test

    public void successBuy(){


        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        buy.success();

    }

    @Test

    void sendWithInvalidCardNumber(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm(generateData(cardNumber(invalidCardNumber1()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        buy.error();
    }

    @Test
    void sendWithNumberUnknownToTheDatabase(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm(generateData(cardNumber(invalidCardNumber2()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        buy.error();
    }

    @Test
    void sendWithMonthMore12(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm(generateData(cardNumber(validCardNumber()), month(invalidMonth()), 1, name(randomName()), cvv(cvc1())));
        buy.invalidValidityPeriod();
    }

    @Test
    void sendWithOneFigureMonth(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm(generateData(cardNumber(validCardNumber()), invalidFormatMonth(1), 1, name(randomName()), cvv(cvc1())));
        buy.invalidFormat();
    }

    @Test
    void sendWithYearLessCurrent(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(1)), -1, name(randomName()), cvv(cvc1())));
        buy.expired();

    }

    @Test
    void sendWithMonthLessCurrentAndCurrentYear(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(-1)), 0, name(randomName()), cvv(cvc1())));
        buy.expired();
    }

    @Test
    void sendWithYearMorePlus5(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(1)), 6, name(randomName()), cvv(cvc1())));
        buy.invalidValidityPeriod();
    }

    @Test
    void sendWithCyrillicName(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(1)), 1, name(rusName()), cvv(cvc1())));
        buy.invalidFormat();
    }

    @Test
    void sendWithDigitInName(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(1)), 1, name(nameWitsDigit()), cvv(cvc1())));
        buy.invalidFormat();
    }

    @Test
    void sendWithSymbolInName(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(1)), 1, name(naneWithSymbol()), cvv(cvc1())));
        buy.invalidFormat();
    }

    @Test
    void sendWithTwoDigitsInCvv(){
            var mainPage = new MainPage();
            mainPage.payByCard();
            var buy = new BuyPage();
            buy.sendForm(generateData(cardNumber(validCardNumber()), month(validMonth(1)), 1, name(randomName()), cvv(invalidCvc())));
            buy.invalidFormat();
        }

        @Test
    void sendWithOutNumber(){
            var mainPage = new MainPage();
            mainPage.payByCard();
            var buy = new BuyPage();
            buy.sendFormWithOutNumber(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
            buy.blankField();
        }

    @Test
    void sendWithOutMonth(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendFormWithOutMonth(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        buy.blankField();
    }

    @Test
    void sendWithOutYear(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendFormWithOutYear(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        buy.blankField();
    }

    @Test
    void sendWithOutName(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendFormWithOutName(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        buy.blankField();
    }

    @Test
    void sendWithOutCvv(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendFormWithOutCvv(generateData(cardNumber(validCardNumber()), validMonth(1), 1, name(randomName()), cvv(cvc1())));
        buy.blankField();
    }

    @Test
    void sendBlankForm(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendBlankForm();
        buy.blankFields();
    }
}



   /* @Test
    void BuySendWithInvalidStatus(){

        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.sendForm();
    }

    @Test
    void BuySendWithExpired1(){

        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.expired1();
    }

    @Test
    void BuySendWithExpired2(){

        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.expired2();
    }

    @Test
    void BuySendWithTooFarYear(){

        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.notTrueYear();
    }

    @Test
    void buySendWithJustFirstName(){

        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.justFirstName();
    }
    @Test
    void buySendWithCyrillicName(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.nameOnCyrillic();
    }

    @Test
    void buySendWithNameWithDigit() {
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.nameWithDigit();
    }

    @Test
    void buySendWithNameWithSymbol() {
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.nameWithSymbol();
    }

    @Test
    void buySendWithIncorrectCvv(){

            var mainPage = new MainPage();
            mainPage.payByCard();
        var buy = new BuyPage();
        buy.invalidCvv();
    }

    @Test
    void buySendWithBlankName(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.blankName();
    }

    @Test
    void buySendWithBlankNumber(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.blankNumber();

    }

    @Test
    void buySendWithBlankMonth(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.blankMonth();
    }

    @Test
    void buySendWithBlankYear(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.blankYear();
    }

    @Test
    void buySendWithBlankCvv(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.blankCvv();
    }

    @Test
    void buySendBlankForm(){
        var mainPage = new MainPage();
        mainPage.payByCard();
        var buy = new BuyPage();
        buy.blankForm();
    }

    @Test

    public void successBuyCredit(){


        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.successBuy();

    }

    @Test
    void sendCreditWithInvalidStatus(){

        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.blockStatus();
    }

    @Test
    void sendCreditWithExpired1(){

        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.expired1();
    }

    @Test
    void sendCreditWithExpired2(){

        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.expired2();
    }

    @Test
    void sendCreditWithTooFarYear(){

        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.notTrueYear();
    }

    @Test
    void sendCreditWithJustFirstName(){

        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.justFirstName();
    }
    @Test
    void sendCreditWithCyrillicName(){
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.nameOnCyrillic();
    }

    @Test
    void sendCreditWithNameWithDigit() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.nameWithDigit();
    }

    @Test
    void sendCreditWithNameWithSymbol() {
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.nameWithSymbol();
    }

    @Test
    void sendCreditWithIncorrectCvv(){

        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.invalidCvv();
    }

    @Test
    void sendCreditWithBlankName(){
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.blankName();
    }

    @Test
    void sendCreditWithBlankNumber(){
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.blankNumber();

    }

    @Test
    void sendCreditWithBlankMonth(){
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.blankMonth();
    }

    @Test
    void sendCreditWithBlankYear(){
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.blankYear();
    }

    @Test
    void sendCreditWithBlankCvv(){
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.blankCvv();
    }

    @Test
    void sendCreditBlankForm(){
        var mainPage = new MainPage();
        mainPage.payByCredit();
        var credit = new CreditPage();
        credit.blankForm();
    }*/



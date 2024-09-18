package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.Value;
import ru.netology.data.CardData;
import ru.netology.data.CardGenerator;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class BuyPage {
    private final SelenideElement payByCard = $(byText("Оплата по карте"));

    private final ElementsCollection input = $$(("span.input__inner"));
    private final SelenideElement cardNumber = input.findBy(Condition.text("Номер карты"))
            .$("input.input__control");
    private final SelenideElement month = input.findBy(Condition.text("Месяц"))
            .$("input.input__control");
    private final SelenideElement year = input.findBy(Condition.text("Год"))
            .$("input.input__control");
    private final SelenideElement name = input.findBy(Condition.text("Владелец"))
            .$("input.input__control");
    private final SelenideElement cvv = input.findBy(Condition.text("CVC/CVV"))
            .$("input.input__control");

    private final ElementsCollection buttons = $$(".button");
    private final SelenideElement send = buttons.findBy(Condition.text("Продолжить"));
    private final SelenideElement successSend = $(byText("Операция одобрена Банком."));
    private final SelenideElement errorSend = $(byText("Ошибка! Банк отказал в проведении операции."));

    /*private final CardData validUser = CardGenerator.validData();
    private final CardData invalidUser1 = CardGenerator.invalidStatus();
    private final CardData invalidUser2 = CardGenerator.invalidDate1();
    private final CardData invalidUser3 = CardGenerator.invalidDate2();
    private final CardData invalidUser4 = CardGenerator.invalidDate3();
    private final CardData invalidUser5 = CardGenerator.invalidUser1();
    private final CardData invalidUser6 = CardGenerator.invalidUser2();
    private final CardData invalidUser7 = CardGenerator.invalidUser3();
    private final CardData invalidUser8 = CardGenerator.invalidUser4();
    private final CardData invalidUser9 = CardGenerator.invalidCvv();
    private final CardData invalidUser10 = CardGenerator.invalidData();
*/
    public BuyPage(){
        payByCard.shouldBe(Condition.visible);
    }

    /* public void successBuy (){
        cardNumber.sendKeys(validUser.getCardNumber());
        month.sendKeys(validUser.getMonth());
        year.sendKeys(validUser.getYear());
        name.sendKeys(validUser.getName());
        cvv.sendKeys(validUser.getCvv());
        send.click();
        successSend.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void blockStatus(){
        cardNumber.sendKeys(invalidUser1.getCardNumber());
        month.sendKeys(invalidUser1.getMonth());
        year.sendKeys(invalidUser1.getYear());
        name.sendKeys(invalidUser1.getName());
        cvv.sendKeys(invalidUser1.getCvv());
        send.click();
        errorSend.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void expired1(){
        cardNumber.sendKeys(invalidUser2.getCardNumber());
        month.sendKeys(invalidUser2.getMonth());
        year.sendKeys(invalidUser2.getYear());
        name.sendKeys(invalidUser2.getName());
        cvv.sendKeys(invalidUser2.getCvv());
        send.click();
        $(withText("Неверно указан срок действия карты")).shouldBe(Condition.visible);
    }


    public void expired2(){
        cardNumber.sendKeys(invalidUser3.getCardNumber());
        month.sendKeys(invalidUser3.getMonth());
        year.sendKeys(invalidUser3.getYear());
        name.sendKeys(invalidUser3.getName());
        cvv.sendKeys(invalidUser3.getCvv());
        send.click();
        $(withText("Истёк срок действия карты")).shouldBe(Condition.visible);
    }

    public void notTrueYear(){
        cardNumber.sendKeys(invalidUser4.getCardNumber());
        month.sendKeys(invalidUser4.getMonth());
        year.sendKeys(invalidUser4.getYear());
        name.sendKeys(invalidUser4.getName());
        cvv.sendKeys(invalidUser4.getCvv());
        send.click();
        $(withText("Неверно указан срок действия карты")).shouldBe(Condition.visible);
    }

    public void justFirstName(){
        cardNumber.sendKeys(invalidUser5.getCardNumber());
        month.sendKeys(invalidUser5.getMonth());
        year.sendKeys(invalidUser5.getYear());
        name.sendKeys(invalidUser5.getName());
        cvv.sendKeys(invalidUser5.getCvv());
        send.click();
        $(withText("Неверный формат")).shouldBe(Condition.visible);
    }

    public void nameOnCyrillic(){
        cardNumber.sendKeys(invalidUser6.getCardNumber());
        month.sendKeys(invalidUser6.getMonth());
        year.sendKeys(invalidUser6.getYear());
        name.sendKeys(invalidUser6.getName());
        cvv.sendKeys(invalidUser6.getCvv());
        send.click();
        $(withText("Неверный формат")).shouldBe(Condition.visible);
    }

    public void nameWithDigit(){
        cardNumber.sendKeys(invalidUser7.getCardNumber());
        month.sendKeys(invalidUser7.getMonth());
        year.sendKeys(invalidUser7.getYear());
        name.sendKeys(invalidUser7.getName());
        cvv.sendKeys(invalidUser7.getCvv());
        send.click();
        $(withText("Неверный формат")).shouldBe(Condition.visible);
    }

    public void nameWithSymbol(){
        cardNumber.sendKeys(invalidUser8.getCardNumber());
        month.sendKeys(invalidUser8.getMonth());
        year.sendKeys(invalidUser8.getYear());
        name.sendKeys(invalidUser8.getName());
        cvv.sendKeys(invalidUser8.getCvv());
        send.click();
        $(withText("Неверный формат")).shouldBe(Condition.visible);
    }

    public void invalidCvv(){
        cardNumber.sendKeys(invalidUser9.getCardNumber());
        month.sendKeys(invalidUser9.getMonth());
        year.sendKeys(invalidUser9.getYear());
        name.sendKeys(invalidUser9.getName());
        cvv.sendKeys(invalidUser9.getCvv());
        send.click();
        $(withText("Неверный формат")).shouldBe(Condition.visible);
    }

    public void cardNumberNotFromDataBase(){
        cardNumber.sendKeys(invalidUser10.getCardNumber());
        month.sendKeys(invalidUser10.getMonth());
        year.sendKeys(invalidUser10.getYear());
        name.sendKeys(invalidUser10.getName());
        cvv.sendKeys(invalidUser10.getCvv());
        send.click();
        errorSend.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void blankNumber(){
        month.sendKeys(validUser.getMonth());
        year.sendKeys(validUser.getYear());
        name.sendKeys(validUser.getName());
        cvv.sendKeys(validUser.getCvv());
        send.click();
        $(withText("Поле обязательно для заполнения")).shouldBe(Condition.visible);
    }

    public void blankMonth(){
        cardNumber.sendKeys(validUser.getCardNumber());
        year.sendKeys(validUser.getYear());
        name.sendKeys(validUser.getName());
        cvv.sendKeys(validUser.getCvv());
        send.click();
        $(withText("Поле обязательно для заполнения")).shouldBe(Condition.visible);
    }

    public void blankYear(){
        cardNumber.sendKeys(validUser.getCardNumber());
        month.sendKeys(validUser.getMonth());
        name.sendKeys(validUser.getName());
        cvv.sendKeys(validUser.getCvv());
        send.click();
        $(withText("Поле обязательно для заполнения")).shouldBe(Condition.visible);
    }

    public void blankName(){
        cardNumber.sendKeys(validUser.getCardNumber());
        month.sendKeys(validUser.getMonth());
        year.sendKeys(validUser.getYear());
        cvv.sendKeys(validUser.getCvv());
        send.click();
        $(withText("Поле обязательно для заполнения")).shouldBe(Condition.visible);
    }

    public void blankCvv(){
        cardNumber.sendKeys(validUser.getCardNumber());
        month.sendKeys(validUser.getMonth());
        year.sendKeys(validUser.getYear());
        name.sendKeys(validUser.getName());
        send.click();
        $(withText("Поле обязательно для заполнения")).shouldBe(Condition.visible);
    }

    public void blankForm(){
        send.click();
        $$(withText("Поле обязательно для заполнения")).shouldHave(size(5));
    }
     */
    public void sendForm(CardData cardData){
        cardNumber.sendKeys(cardData.getCardNumber());
        month.sendKeys(cardData.getMonth());
        year.sendKeys(cardData.getYear());
        name.sendKeys(cardData.getName());
        send.click();
    }
}

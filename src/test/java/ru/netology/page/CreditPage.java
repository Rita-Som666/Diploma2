package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import ru.netology.data.CardData;


import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class CreditPage {
    private final SelenideElement payByCredit = $(byText("Кредит по данным карты"));
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
    private final SelenideElement invalidFormat = $(withText("Неверный формат"));
    private final SelenideElement invalidValidityPeriod = $(withText("Неверно указан срок действия карты"));
    private final SelenideElement blankField = $(withText("Поле обязательно для заполнения"));
    private final SelenideElement expired = $(withText("Истёк срок действия карты"));


    public CreditPage() {
        payByCredit.shouldBe(Condition.visible);
    }


    public void sendForm(CardData cardData) {
        cardNumber.sendKeys(cardData.getCardNumber());
        month.sendKeys(cardData.getMonth());
        year.sendKeys(cardData.getYear());
        name.sendKeys(cardData.getName());
        cvv.sendKeys(cardData.getCvv());
        send.click();
    }

    public void sendFormWithOutNumber(CardData cardData) {
        month.sendKeys(cardData.getMonth());
        year.sendKeys(cardData.getYear());
        name.sendKeys(cardData.getName());
        cvv.sendKeys(cardData.getCvv());
        send.click();
    }

    public void sendFormWithOutMonth(CardData cardData) {
        cardNumber.sendKeys(cardData.getCardNumber());

        year.sendKeys(cardData.getYear());
        name.sendKeys(cardData.getName());
        cvv.sendKeys(cardData.getCvv());
        send.click();
    }

    public void sendFormWithOutYear(CardData cardData) {
        cardNumber.sendKeys(cardData.getCardNumber());
        month.sendKeys(cardData.getMonth());

        name.sendKeys(cardData.getName());
        cvv.sendKeys(cardData.getCvv());
        send.click();
    }

    public void sendFormWithOutName(CardData cardData) {
        cardNumber.sendKeys(cardData.getCardNumber());
        month.sendKeys(cardData.getMonth());
        year.sendKeys(cardData.getYear());

        cvv.sendKeys(cardData.getCvv());
        send.click();
    }

    public void sendFormWithOutCvv(CardData cardData) {
        cardNumber.sendKeys(cardData.getCardNumber());
        month.sendKeys(cardData.getMonth());
        year.sendKeys(cardData.getYear());
        name.sendKeys(cardData.getName());

        send.click();
    }

    public void sendBlankForm() {
        send.click();
    }

    public void blankFields() {
        $$(withText("Поле обязательно для заполнения")).shouldHave(size(5));
    }


    public void success() {
        successSend.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void error() {
        errorSend.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void invalidFormat() {
        invalidFormat.shouldBe(Condition.visible);
    }

    public void invalidValidityPeriod() {
        invalidValidityPeriod.shouldBe(Condition.visible);
    }

    public void blankField() {
        blankField.shouldBe(Condition.visible);
    }

    public void expired() {
        expired.shouldBe(Condition.visible);
    }
}

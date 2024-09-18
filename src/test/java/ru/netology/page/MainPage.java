package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Value
public class MainPage {

    private SelenideElement buy = $(byText("Купить"));
    private SelenideElement buyOnCredit = $(byText("Купить в кредит"));

    public BuyPage payByCard (){
        buy.click();
        return new BuyPage();
    }

    public CreditPage payByCredit(){

        buyOnCredit.click();
        return new CreditPage();
    }
}


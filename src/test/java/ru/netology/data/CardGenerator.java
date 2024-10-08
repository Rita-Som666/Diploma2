package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


@Value
public class CardGenerator {

    public static String name(String name) {
        return name;
    }

    public static String randomName() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName() + " " + faker.name().lastName();

    }

    public static String firstName() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().firstName();

    }

    public static String rusName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();

    }

    public static String nameWitsDigit() {
        return "ivan1 ivanov";

    }

    public static String naneWithSymbol() {
        return "ivan ivanov.";

    }

    public static String validMonth(int plusMonths) {

        return LocalDate.now().plusMonths(plusMonths).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String invalidFormatMonth(int plusMonths) {

        return LocalDate.now().plusMonths(plusMonths).format(DateTimeFormatter.ofPattern("M"));
    }

    public static String invalidMonth() {
        Random random = new Random();
        return String.valueOf(12 + random.nextInt(88));
    }

    public static String month(String month) {
        return month;
    }

    public static String year(int plusYears) {

        return LocalDate.now().plusYears(plusYears).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String cardNumber(String cardNumber) {
        return cardNumber;
    }


    public static String validCardNumber() {

        return "4444444444444441";
    }

    public static String invalidCardNumber1() {

        return "4444444444444442";
    }

    public static String invalidCardNumber2() {

        Faker faker = new Faker();

        return faker.finance().creditCard();
    }

    public static String cvv(String cvc) {

        return cvc;
    }

    public static String cvc1() {
        Random random = new Random();
        return String.valueOf(100 + random.nextInt(900));
    }

    public static String invalidCvc() {
        Random random = new Random();
        return String.valueOf(10 + random.nextInt(90));
    }


    public static CardData generateData(String cardNumber, String month, int plusYear, String name, String cvv) {
        return new CardData(cardNumber(cardNumber), name(name), month(month), year(plusYear), cvv(cvv));
    }


}



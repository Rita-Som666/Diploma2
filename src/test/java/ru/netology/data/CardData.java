package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardData {
    private String cardNumber;
    private String name;
    private String month;
    private String year;
    private String cvv;
}
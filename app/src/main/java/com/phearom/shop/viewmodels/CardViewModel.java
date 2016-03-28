package com.phearom.shop.viewmodels;

import android.databinding.BaseObservable;

import com.phearom.shop.models.Card;

/**
 * Created by phearom on 3/25/16.
 */
public class CardViewModel extends BaseObservable {
    private final Card card;

    public CardViewModel(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public String getNumber() {
        return card.getNumber();
    }

    public String getSerial() {
        return card.getSerial();
    }
}

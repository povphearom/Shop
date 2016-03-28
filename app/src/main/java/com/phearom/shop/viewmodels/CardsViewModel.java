package com.phearom.shop.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.phearom.shop.BR;
import com.phearom.shop.models.Card;

/**
 * Created by phearom on 3/25/16.
 */
public class CardsViewModel extends BaseObservable {
    @Bindable
    public final ObservableList<CardViewModel> items;
    private boolean failed = false;

    public CardsViewModel() {
        items = new ObservableArrayList<>();
    }

    public void add(Card card) {
        if (!items.contains(new CardViewModel(card)))
            items.add(new CardViewModel(card));
    }

    public void add(CardViewModel card) {
        if (!items.contains(card))
            items.add(card);
    }

    public CardViewModel getItem(int index) {
        return items.get(index);
    }

    @Bindable
    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
        notifyPropertyChanged(BR.failed);
    }
}

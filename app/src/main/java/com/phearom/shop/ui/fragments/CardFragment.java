package com.phearom.shop.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.phearom.shop.BR;
import com.phearom.shop.R;
import com.phearom.shop.api.bindapi.binder.CompositeItemBinder;
import com.phearom.shop.api.bindapi.binder.ItemBinder;
import com.phearom.shop.api.bindapi.listener.ClickHandler;
import com.phearom.shop.api.utils.SpacesItemDecoration;
import com.phearom.shop.binder.CardBinder;
import com.phearom.shop.databinding.FragmentCardBinding;
import com.phearom.shop.models.Card;
import com.phearom.shop.viewmodels.CardViewModel;
import com.phearom.shop.viewmodels.CardsViewModel;

import java.util.UUID;

/**
 * Created by phearom on 3/21/16.
 */
public class CardFragment extends Fragment {
    private FragmentCardBinding mBinding;

    private CardsViewModel mCards;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_card, container, false);

        mCards = new CardsViewModel();
        mBinding.setView(this);
        mBinding.setCards(mCards);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding.unbind();
        mBinding = null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerCard.addItemDecoration(new SpacesItemDecoration(8));

        mCards.setFailed(false);
        Card card;
        for (int i = 1; i < 11; i++) {
            card = new Card();
            card.setNumber(String.valueOf(System.currentTimeMillis()));
            card.setSerial(UUID.randomUUID().toString());
            mCards.add(card);
        }

        initListener();
    }

    private void initListener() {
        mBinding.fabCardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "We are developing....", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ClickHandler<CardViewModel> clickHandler() {
        return new ClickHandler<CardViewModel>() {
            @Override
            public void onClick(CardViewModel viewModel, View v) {
            }
        };
    }

    public ItemBinder<CardViewModel> itemViewBinder() {
        return new CompositeItemBinder<>(
                new CardBinder.Body(BR.card, R.layout.item_card)
        );
    }
}

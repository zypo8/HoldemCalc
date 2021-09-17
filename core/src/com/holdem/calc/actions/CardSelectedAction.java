package com.holdem.calc.actions;

import static com.holdem.calc.MyGdxGame.cardSelectEntity;

import com.badlogic.ashley.core.Entity;
import com.holdem.calc.MyGdxGame;
import com.holdem.calc.components.RenderComponenet;
import com.holdem.calc.entities.Card;
import com.holdem.calc.entities.CardSelectEntity;
import com.holdem.calc.entities.CardSlot;

public class CardSelectedAction extends Action{
    public Card card;

    public CardSelectedAction(Card card) {
        this.card = card;
    }

    @Override
    public boolean act() {
        CardSlot.lastClickedCardSlot.setCard(card);

        cardSelectEntity.hide();
        return true;
    }

}

package com.holdem.calc.actions;

import static com.holdem.calc.MyGdxGame.cardSelectEntity;
import static com.holdem.calc.MyGdxGame.cards;

import com.badlogic.ashley.core.Entity;
import com.holdem.calc.components.InputHandlingComponent;
import com.holdem.calc.components.RenderComponenet;
import com.holdem.calc.entities.CardSlot;

public class ShowCardSelectAction extends Action{
    CardSlot cardSlot;
    public ShowCardSelectAction(CardSlot cardSlot) {
        this.cardSlot = cardSlot;
    }

    @Override
    public boolean act() {
        CardSlot.lastClickedCardSlot = cardSlot;
        if (CardSlot.lastClickedCardSlot != null && CardSlot.lastClickedCardSlot.card != null && CardSlot.lastClickedCardSlot.getComponent(InputHandlingComponent.class).touched) {
            CardSlot.lastClickedCardSlot.clear();
            return true;
        }
        if (!cardSelectEntity.getComponent(RenderComponenet.class).isVisible) {
            cardSelectEntity.show();
        }
        else cardSelectEntity.hide();
        return true;
    }

}

package com.holdem.calc.actions;

import com.badlogic.gdx.Gdx;
import com.holdem.calc.Calculator;
import com.holdem.calc.MyGdxGame;
import com.holdem.calc.components.PositionComponent;
import com.holdem.calc.entities.Button;
import com.holdem.calc.entities.Card;
import com.holdem.calc.entities.CardSlot;
import com.holdem.calc.entities.Hand;

import java.util.ArrayList;

public class BTNAction extends Action{

    Button button;
    public BTNAction(Button button) {
        super();
        this.button = button;
    }
    @Override
    public boolean act() {
        button.BtnState = Button.BTNstate.Pressed;
        button.refreshBtnTexture();
        ArrayList<String> HAND = new ArrayList<>(2);
        ArrayList<String> TABLE = new ArrayList<>(5);
        for(Card card : CardSlot.handCards)
            HAND.add(card.toString().substring(5, card.toString().length()-4));
        for(Card card : CardSlot.tableCards)
            TABLE.add(card.toString().substring(5, card.toString().length()-4));
        Gdx.app.log("hand ",HAND.toString());
        Gdx.app.log("table", TABLE.toString());

        Calculator calculator = new Calculator(HAND, TABLE);
        if (!calculator.calculate()) return false;

        MyGdxGame.winningHandsContainer.setHands(calculator.getWinningHands());
        MyGdxGame.winningHandsContainer.show();

        MyGdxGame.losingHandsContainer.setHands(calculator.getLosingHands());
        MyGdxGame.losingHandsContainer.show();
        return true;
    }

    @Override
    public boolean hover() {
        button.BtnState = Button.BTNstate.Hoverd;
        button.refreshBtnTexture();
        return false;
    }

    @Override
    public boolean hoverOf() {
        button.BtnState = Button.BTNstate.Defoult;
        System.out.println("off");
        button.refreshBtnTexture();
        return false;
    }
}

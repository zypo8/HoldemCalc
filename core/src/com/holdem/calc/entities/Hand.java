package com.holdem.calc.entities;

import com.badlogic.ashley.core.Entity;
import com.holdem.calc.MyGdxGame;
import com.holdem.calc.components.PositionComponent;
import com.holdem.calc.components.RenderComponenet;

public class Hand extends Entity {
    public HandsContainer.HandsContainerType handsContainerType;
    public Card Card1,Card2;
    public Hand(Card card1, Card card2, HandsContainer.HandsContainerType ht){
        this.handsContainerType = ht;
        this.Card1 = card1;
        this.Card2 = card2;
        MyGdxGame.engine.addEntity(this);
        this.add(new RenderComponenet("Hand.png", 0));

    }
    public void setPos(float x, float y){
        this.add(new PositionComponent(x, y, 140, 110));
        Card1.setPosition(x+8, y+3);
        Card2.setPosition(x+ 60+8, y+3);
    }

}

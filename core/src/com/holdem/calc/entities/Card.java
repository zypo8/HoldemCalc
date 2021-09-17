package com.holdem.calc.entities;

import com.badlogic.ashley.core.Entity;
import com.holdem.calc.actions.Action;
import com.holdem.calc.components.InputHandlingComponent;
import com.holdem.calc.components.PositionComponent;
import com.holdem.calc.components.RenderComponenet;

public class Card extends Entity {
    public CardSlot cardSlot;
    public Card(String fileName){
        this.add(new RenderComponenet(fileName, 1))
                .add(new InputHandlingComponent());
    }
    public void setInputAction(Action action){
        this.getComponent(InputHandlingComponent.class).action = action;
    }

    public void setPosition(float x, float y){
        this.add(new PositionComponent(x, y, 60, 100));
    }
    public void setCardSlot(CardSlot cardSlot){
        this.cardSlot = cardSlot;
    }

    @Override
    public String toString() {
        return this.getComponent(RenderComponenet.class).fileName;
    }
}

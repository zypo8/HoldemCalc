package com.holdem.calc.entities;

import static com.holdem.calc.MyGdxGame.cards;

import com.badlogic.ashley.core.Entity;
import com.holdem.calc.actions.CardSelectedAction;
import com.holdem.calc.components.InputHandlingComponent;
import com.holdem.calc.components.PositionComponent;
import com.holdem.calc.components.RenderComponenet;

public class CardSelectEntity extends Entity {
    public float x, y;
    public CardSelectEntity(float x, float y) {
        this.x = x;
        this.y = y;
        this.add(new PositionComponent(x, y, 910, 440));
        this.add(new RenderComponenet("Table.png", 0));
        this.getComponent(RenderComponenet.class).isVisible = false;
    }

    public void show(){
        this.getComponent(RenderComponenet.class).isVisible = true;
        for (int i = 0; i < 52; i++) {
            if (CardSlot.usedCards.contains(cards.get(i))) continue;
            float cardX = 70 * i + ((int) i / 13) * -910+ 5;
            float cardY = 110 * ((int) i / 13) + 5;
            cards.get(i).add(new PositionComponent(cardX+x , cardY+y, 60, 100));
            cards.get(i).add(new InputHandlingComponent(new CardSelectedAction(cards.get(i))));
        }
    }

    public void hide(){
        this.getComponent(RenderComponenet.class).isVisible = false;
        for (int i = 0; i < 52; i++) {
            if (CardSlot.usedCards.contains(cards.get(i))) continue;
            cards.get(i).remove(PositionComponent.class);
            cards.get(i).remove(InputHandlingComponent.class);
        }
    }
}

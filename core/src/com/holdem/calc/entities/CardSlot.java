package com.holdem.calc.entities;

import com.badlogic.ashley.core.Entity;
import com.holdem.calc.actions.Action;
import com.holdem.calc.actions.ShowCardSelectAction;
import com.holdem.calc.components.InputHandlingComponent;
import com.holdem.calc.components.PositionComponent;
import com.holdem.calc.components.RenderComponenet;

import java.util.ArrayList;

public class CardSlot extends Entity {
    public static CardSlot lastClickedCardSlot;
    public static ArrayList<Card> usedCards = new ArrayList<>();
    public static ArrayList<Card> handCards = new ArrayList<>();
    public static ArrayList<Card> tableCards = new ArrayList<>();
    public SlotType slotType;
    public Card card;
    public float x, y;

    public CardSlot(float x, float y, SlotType slotType){
        this.x = x;
        this.y = y;
        this.add(new PositionComponent(x, y, 70, 110));
        this.add(new RenderComponenet("CardSlot.png", 0));
        this.add(new InputHandlingComponent(new ShowCardSelectAction(this)));
        this.getComponent(RenderComponenet.class).isVisible = true;
        this.slotType = slotType;

    }

    public void setCard(Card card){
        this.card = card;
        usedCards.add(card);
        if( slotType == SlotType.Hand) handCards.add(card);
        else  if(slotType == SlotType.Table) tableCards.add(card);
        card.add(new PositionComponent(x+5, y+5, 60, 100));
        card.remove(InputHandlingComponent.class);
    }

    public void clear(){
        usedCards.remove(card);
        if( slotType == SlotType.Hand) handCards.remove(card);
        else  if(slotType == SlotType.Table) tableCards.remove(card);
        card.remove(PositionComponent.class);
        this.card = null;
    }
    public void setInputAction(Action action){
        this.getComponent(InputHandlingComponent.class).action = action;
    }

    public void setPosition(float x, float y){
        this.add(new PositionComponent(x, y, 60, 100));
    }
    public enum SlotType{
        Hand, Table;
    }
}

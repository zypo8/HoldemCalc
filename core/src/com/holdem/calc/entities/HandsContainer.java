package com.holdem.calc.entities;

import static com.holdem.calc.MyGdxGame.cards;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.holdem.calc.actions.Action;
import com.holdem.calc.actions.CardSelectedAction;
import com.holdem.calc.components.InputHandlingComponent;
import com.holdem.calc.components.PositionComponent;
import com.holdem.calc.components.RenderComponenet;

import java.util.ArrayList;

public class HandsContainer extends Entity {
    public static int WinningHandsCount = 0;
    public static int LosingHandCount = 0;
    public HandsContainerType handsContainerType;
    public ArrayList<Hand> hands = new ArrayList<>();
    public float x = 150;
    public float y_win = Gdx.graphics.getHeight()/2+ 20;
    public float y_lose = 40;

    public HandsContainer(HandsContainerType handsContainerType){
        this.add(new RenderComponenet("Container.png", 1));
        this.getComponent(RenderComponenet.class).isVisible = false;
        this.handsContainerType = handsContainerType;
        if(handsContainerType == HandsContainerType.Win) this.add(new PositionComponent(x, y_win, Gdx.graphics.getWidth()-180, Gdx.graphics.getHeight()/2-40));
        if(handsContainerType == HandsContainerType.Lose) this.add(new PositionComponent(x, y_lose, Gdx.graphics.getWidth()-180, Gdx.graphics.getHeight()/2-40));
    }

    public void setInputAction(Action action){
        this.getComponent(InputHandlingComponent.class).action = action;
    }

    public void setPosition(float x, float y){
        this.add(new PositionComponent(x, y, 60, 100));
    }

    public void setHands(ArrayList<Hand> hands){
        this.hands = hands;
        if( hands.size() == 0) return;
        if(hands.get(0).handsContainerType == HandsContainerType.Win) {
            for (Hand hand : hands) {
                hand.setPos(2+x + 142* (WinningHandsCount % 14), y_win+2 + 112* (WinningHandsCount / 14));
                WinningHandsCount++;
            }
        }
        if(hands.get(0).handsContainerType == HandsContainerType.Lose) {
            for (Hand hand : hands) {
                hand.setPos(2+x + 142* (LosingHandCount % 14), y_lose+2);
                LosingHandCount++;
            }
        }
    }

    public enum HandsContainerType{
        Win,Lose;
    }

    public void show(){
        this.getComponent(RenderComponenet.class).isVisible = true;

    }

    public void hide(){
        this.getComponent(RenderComponenet.class).isVisible = false;
    }


    // first char -> number, secont char -> color
    // S -> doesnt matter what, same type(number, or color) for every card for every card, X -> doesnt matter what, may be diffrent for every card
    public enum CardsRanking{
        Poker("1S", "2S", "3S", "4S", "5S"),
        FourOfaKind("SX", "SX", "SX", "SX", "null"),
        FullHouse("SC", "1T", "1H", "2C", "2H"),
        Flush("1", "2", "3", "4", "5"),
        Strait("1", "2", "3", "4", "5"),
        ThreeOfaKind("1", "2", "3", "4", "5"),
        TwoPair("1", "2", "3", "4", "5"),
        Pair("1", "2", "3", "4", "5"),
        HighCard("1", "2", "3", "4", "5");

        public String card1, card2, card3, card4, card5;
        CardsRanking(String card1,String card2,String card3,String card4,String card5){
            this.card1 = card1;
            this.card2 = card2;
            this.card3 = card3;
            this.card4 = card4;
            this.card5 = card5;
        }
    }
}

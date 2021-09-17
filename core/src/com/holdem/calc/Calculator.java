package com.holdem.calc;

import com.holdem.calc.entities.Card;
import com.holdem.calc.entities.Hand;
import com.holdem.calc.entities.HandsContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Calculator {
    private ArrayList<String> hand;
    private ArrayList<String> table;
    private ArrayList<String> tableColors;
    private ArrayList<String> handColors;
    private ArrayList<String> wholeColors;

    public ArrayList<Hand> winningHand;
    public ArrayList<Hand> losingHand;

    public Calculator(ArrayList<String> h, ArrayList<String> t){
        this.hand = h;
        this.table = t;
    }

    public boolean calculate(){
        if ((hand == null) || (table == null))return false;
        setUpCardInfo();
        System.out.println("DUPSKO"+wholeColors.size());
        // winning hands
        Card card1 = new Card("deck/1C.png");
        MyGdxGame.engine.addEntity(card1);
        Card card2 = new Card("deck/7C.png");
        MyGdxGame.engine.addEntity(card2);

        Card card3 = new Card("deck/2T.png");
        MyGdxGame.engine.addEntity(card3);
        Card card4 = new Card("deck/3C.png");
        MyGdxGame.engine.addEntity(card4);

        Hand hand1 = new Hand(card1, card2, HandsContainer.HandsContainerType.Win);
        Hand hand2 = new Hand(card3, card4, HandsContainer.HandsContainerType.Win);
        ArrayList<Hand> arrayList = new ArrayList<>(2);
        arrayList.add(hand1);
        arrayList.add(hand2);
        winningHand = arrayList;

        // losing hands
        losingHand = new ArrayList<>(1);
        return true;
    }

    private void setUpCardInfo() {
        tableColors = new ArrayList<>(4);
        handColors = new ArrayList<>(4);
        wholeColors = new ArrayList<>(4);
        Collections.sort(table, new CardComparator());
        Collections.sort(hand, new CardComparator());
        //gowno
//        for ( String card: table) if(!table.contains(card.substring(1,1))) tableColors.add(card.substring(1,1));
//        for ( String card: hand) if(!table.contains(card.substring(1,1))) handColors.add(card.substring(1,1));
//        ArrayList<String> WholeCards = new ArrayList<>(hand);
//        wholeColors.addAll(table);
//        for ( String card: WholeCards) if(!table.contains(card.substring(1,1))) wholeColors.add(card.substring(1,1));
    }


    public ArrayList getWinningHands(){ return winningHand; }
    public ArrayList getLosingHands(){
        return losingHand;
    }
}

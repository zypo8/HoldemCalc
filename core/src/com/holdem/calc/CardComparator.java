package com.holdem.calc;

import com.holdem.calc.components.RenderComponenet;
import com.holdem.calc.entities.Card;

import java.util.Comparator;

public class CardComparator implements Comparator {
    @Override
    public int compare(Object o, Object t1) {
        String card1 = (String) o;
        String card2 = (String) t1;
        int card1No = Integer.valueOf(card1.substring(0, 1));
        int card2No = Integer.valueOf(card2.substring(0, 1));
        if( card1No >= card2No){
            return 1;
        }else if( card1No < card2No){
            return -1;
        }else return 0;
    }
}

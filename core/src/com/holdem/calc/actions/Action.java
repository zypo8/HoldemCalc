package com.holdem.calc.actions;


public abstract class Action {
    public boolean HoverOfDone = true;

    public abstract boolean act();

    public boolean hover() {
        HoverOfDone = false;
        return false;
    }

    public boolean hoverOf() {
        HoverOfDone = true;
        return false;
    }
}

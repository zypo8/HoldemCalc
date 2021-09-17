package com.holdem.calc.actions;


import com.badlogic.gdx.Gdx;

public class TestAction extends Action{

    @Override
    public boolean act() {
        Gdx.app.log("act", "act method working");
        return false;
    }

    @Override
    public boolean hover() {
        Gdx.app.log("act", "Hovered");
        return false;
    }

    @Override
    public boolean hoverOf() {
        Gdx.app.log("act", "Hovered of");
        return false;
    }
}

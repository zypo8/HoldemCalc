package com.holdem.calc.components;

import com.badlogic.ashley.core.Component;
import com.holdem.calc.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputHandlingComponent implements Component {
    public boolean touched = false;
    public boolean hoverd = false;
    public Action action;

    public InputHandlingComponent(){}


    public InputHandlingComponent(Action action){
        this.action = action;
    }

    public void addAction(Action action){
        this.action = action;
    }

    public void resetActions() {
        action = null;
    }
}

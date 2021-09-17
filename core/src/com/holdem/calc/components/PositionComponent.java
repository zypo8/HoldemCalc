package com.holdem.calc.components;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {
    public float x, y, width, height;
    public PositionComponent(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}

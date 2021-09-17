package com.holdem.calc.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TextRenderComponenet implements Component {
    public String text;
    public boolean isVisible = true;
    public int Zindex;
    // aligment.x -1 = down, 0 = mid, 1 = top & y = -1 = left... y = 1 = right;
    public TextRenderComponenet(String text, int Zindex, Vector2 aligment){
        this.Zindex = Zindex;
    }
}

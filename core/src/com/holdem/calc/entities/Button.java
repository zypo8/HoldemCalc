package com.holdem.calc.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.holdem.calc.actions.Action;
import com.holdem.calc.actions.BTNAction;
import com.holdem.calc.actions.TestAction;
import com.holdem.calc.components.InputHandlingComponent;
import com.holdem.calc.components.PositionComponent;
import com.holdem.calc.components.RenderComponenet;

import java.util.ArrayList;


public class Button extends Entity {
    public Texture texture;
    public ArrayList<TextureRegion> BTNstateVisual = new ArrayList<>(3);

    public BTNstate BtnState = BTNstate.Defoult;

    public Button(float x, float y) {
        texture = new Texture(Gdx.files.internal("button.png"));
        BTNstateVisual.add(new TextureRegion(texture, 0, 60, 60, 30));
        BTNstateVisual.add(new TextureRegion(texture, 0, 30, 60, 30));
        BTNstateVisual.add(new TextureRegion(texture, 0, 0, 60, 30));
        this.add(new RenderComponenet(BTNstateVisual.get(BtnState.i), 1));
        this.add(new PositionComponent(x, y, 60, 30));
        this.add(new InputHandlingComponent(new BTNAction(this)));

    }

    // 0 defoult, 1 hover, 2 pressed
    public boolean refreshBtnTexture(){
        this.add(new RenderComponenet(BTNstateVisual.get(BtnState.i), 1));
        //this.add(new InputHandlingComponent());
        return true;

    }

    public void setInputAction(Action action) {
        this.getComponent(InputHandlingComponent.class).action = action;
    }

    public boolean press() {
        if (this.getComponent(InputHandlingComponent.class).action != null) {
            this.getComponent(InputHandlingComponent.class).action.act();
            return true;
        }
        return false;
    }

    public void setPosition(float x, float y) {
        this.add(new PositionComponent(x, y, 60, 30));
    }

    public enum BTNstate {
        Defoult(0), Hoverd(1), Pressed(2);

        private int i;
        BTNstate(int i){
            this.i = i;
        }
    }

}

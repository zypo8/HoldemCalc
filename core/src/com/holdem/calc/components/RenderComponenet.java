package com.holdem.calc.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderComponenet implements Component {
    public boolean UsingTextureRegion = false;
    public TextureRegion textureRegion;
    public Texture texture;
    public int Zindex;
    public String fileName;
    public boolean isVisible = true;
    public RenderComponenet(String fileName, int Zindex){
        texture = new Texture(Gdx.files.internal(fileName));
        this.Zindex = Zindex;
        this.fileName = fileName;
    }

    public RenderComponenet(TextureRegion textureRegion, int Zindex){
        UsingTextureRegion = true;
        this.textureRegion = textureRegion;
        this.Zindex = Zindex;
        this.fileName = textureRegion.getTexture().toString();
    }
}

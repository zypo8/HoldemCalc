package com.holdem.calc.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.holdem.calc.components.CardSelectDisplayComponent;
import com.holdem.calc.components.InputHandlingComponent;
import com.holdem.calc.components.PositionComponent;
import com.holdem.calc.components.RenderComponenet;

public class InputManagerSystem extends EntitySystem implements InputProcessor {
    private ComponentMapper<InputHandlingComponent> Incpm;
    private ComponentMapper<PositionComponent> pcm;
    private ComponentMapper<CardSelectDisplayComponent> csdc;
    private ComponentMapper<RenderComponenet> rdcm;
    public static ImmutableArray<Entity> entities;

    OrthographicCamera camera;

    public InputManagerSystem(OrthographicCamera camera) {
        this.camera = camera;
        Incpm = ComponentMapper.getFor(InputHandlingComponent.class);
        pcm = ComponentMapper.getFor(PositionComponent.class);
        csdc = ComponentMapper.getFor(CardSelectDisplayComponent.class);
        rdcm = ComponentMapper.getFor(RenderComponenet.class);
    }


    @Override
    public void addedToEngine (Engine engine) {
        entities =  engine.getEntitiesFor(Family.all(InputHandlingComponent.class, PositionComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = new Entity();
            if (entity.getComponent(RenderComponenet.class) != null){}
            processEntity(entities.get(i), deltaTime);
        }
    }

    protected void processEntity(Entity entity, float deltaTime) {
        if (Incpm.get(entity) == null) return;
        if (Incpm.get(entity).action == null) return;
        if(Incpm.get(entity).hoverd == true) {
            if (Incpm.get(entity).action != null) {
                Incpm.get(entity).action.hover();
                Incpm.get(entity).action.HoverOfDone = false;
            }
        }else {
            if (!Incpm.get(entity).action.HoverOfDone) {
                Incpm.get(entity).action.hoverOf();
                Incpm.get(entity).action.HoverOfDone = true;
            }
        }
        if(Incpm.get(entity).touched == true) {
            if (Incpm.get(entity).action != null) {
                Incpm.get(entity).action.act();
            }
            if (Incpm.get(entity) == null) return;
            Incpm.get(entity).touched  = false;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //if (cardSelectEntity.isVisible) cardSelectEntity.hide();
        //cardSelectEntity.show();
        for (int i = 0; i < entities.size(); ++i) {
            Entity ent = entities.get(i);
            PositionComponent entPosition = pcm.get(ent);
            Vector3 clickPosition = camera.unproject(new Vector3(screenX, screenY, 0));
            //System.out.println(rdcm.get(ent).fileName);

            if (clickPosition.x < (entPosition.width + entPosition.x) && clickPosition.x > entPosition.x && clickPosition.y > entPosition.y && clickPosition.y < (entPosition.height + entPosition.y)){
                ent.getComponent(InputHandlingComponent.class).hoverd = true;
            }else ent.getComponent(InputHandlingComponent.class).hoverd = false;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("touch up", "touched");
        //if (cardSelectEntity.isVisible) cardSelectEntity.hide();
        //cardSelectEntity.show();
        for (int i = 0; i < entities.size(); ++i) {
            Entity ent = entities.get(i);
            //System.out.println(rdcm.get(ent).fileName);
            PositionComponent entPosition = pcm.get(ent);
            Vector3 clickPosition = camera.unproject(new Vector3(screenX, screenY, 0));
            if (clickPosition.x < (entPosition.width + entPosition.x) && clickPosition.x > entPosition.x && clickPosition.y > entPosition.y && clickPosition.y < (entPosition.height + entPosition.y)){
                ent.getComponent(InputHandlingComponent.class).touched = true;
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity ent = entities.get(i);
            PositionComponent entPosition = pcm.get(ent);
            Vector3 clickPosition = camera.unproject(new Vector3(screenX, screenY, 0));
            //System.out.println(rdcm.get(ent).fileName);

            if (clickPosition.x < (entPosition.width + entPosition.x) && clickPosition.x > entPosition.x && clickPosition.y > entPosition.y && clickPosition.y < (entPosition.height + entPosition.y)){
            }else ent.getComponent(InputHandlingComponent.class).hoverd = false;
        }
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

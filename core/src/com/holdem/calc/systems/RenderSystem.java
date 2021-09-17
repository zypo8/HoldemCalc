package com.holdem.calc.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.holdem.calc.components.PositionComponent;
import com.holdem.calc.components.RenderComponenet;

public class RenderSystem extends SortedIteratingSystem {
    private SpriteBatch batch;

    public RenderSystem(SpriteBatch batch) {
        super(Family.all(PositionComponent.class, RenderComponenet.class).get(), new RenderComparator());
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RenderComponenet renderComponenet = entity.getComponent(RenderComponenet.class);
        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
        if(!renderComponenet.isVisible) return;
        batch.begin();
        if (renderComponenet.UsingTextureRegion)
            batch.draw(renderComponenet.textureRegion, positionComponent.x, positionComponent.y);
        else
            batch.draw(renderComponenet.texture, positionComponent.x, positionComponent.y, positionComponent.width, positionComponent.height);
        batch.end();
    }
}

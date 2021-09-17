package com.holdem.calc.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.holdem.calc.components.RenderComponenet;

import java.util.Comparator;

public class RenderComparator implements Comparator<Entity> {
    private ComponentMapper<RenderComponenet> cmTrans;

    public RenderComparator(){
        cmTrans= ComponentMapper.getFor(RenderComponenet.class);
    }

    @Override
    public int compare(Entity entityA, Entity entityB) {
        int A_EntZ = cmTrans.get(entityA).Zindex;
        int B_EntZ = cmTrans.get(entityB).Zindex;
        int res = 0;
        if(A_EntZ > B_EntZ){
            res = 1;
        }else if(A_EntZ < B_EntZ){
            res = -1;
        }
        return res;
    }
}

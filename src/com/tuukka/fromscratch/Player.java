package com.tuukka.fromscratch;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import com.badlogic.gdx.physics.box2d.Body;

public class Player extends AnimatedSprite {
	
	private Body body;
	
	public Player(float pX, float pY, VertexBufferObjectManager vbom, Camera camera, PhysicsWorld physicsWorld)
    {
        super(pX, pY, ResourcesManager.getInstance().player_region, vbom);
        //createPhysics(camera, physicsWorld);
        camera.setChaseEntity(this);
    }

	public Player(float x, float y, TiledTextureRegion player_region,
			VertexBufferObjectManager vbom) {
		super(x, y, player_region, vbom);
	}

	private void createPhysics(final Camera camera, PhysicsWorld physicsWorld)
	{ 
	    body = PhysicsFactory.createBoxBody(physicsWorld, this, BodyType.DynamicBody, PhysicsFactory.createFixtureDef(0, 0, 0));
		
	}

	public void eat() {
		// TODO Auto-generated method stub
		//this.setCurrentTileIndex(1);
		/*this.setCurrentTileIndex(2);
		this.animate(2000, false);*/
		long durations[] = new long[2];
		durations[0] = (long) 300;
		durations[1] = (long) 400;
		int[] pFrames = new int[2];
		pFrames[0] = 1;
		pFrames[1] = 0;
		
		this.animate(durations, pFrames, 3);
		
	}
}

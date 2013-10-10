package com.tuukka.fromscratch;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import com.badlogic.gdx.physics.box2d.Body;

public class Player extends AnimatedSprite {
	
	private Body body;
	
	public Player(float pX, float pY, VertexBufferObjectManager vbo, Camera camera, PhysicsWorld physicsWorld)
    {
        super(pX, pY, ResourcesManager.getInstance().player_region, vbo);
        createPhysics(camera, physicsWorld);
        camera.setChaseEntity(this);
    }

	private void createPhysics(final Camera camera, PhysicsWorld physicsWorld)
	{ 
		
	    body = PhysicsFactory.createBoxBody(physicsWorld, this, BodyType.DynamicBody, PhysicsFactory.createFixtureDef(0, 0, 0));
		
		
		
	}
}

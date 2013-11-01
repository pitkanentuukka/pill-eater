package com.tuukka.fromscratch;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;

public class BodyNode extends Sprite {

	private DistanceJointDef distanceJointDef = new DistanceJointDef();

	private DistanceJoint joint;
	private Body body;
	private FixtureDef fixtureDef;

	private GameManager gameManager;
	private SceneManager sceneManager;

	public BodyNode(float pX, float pY, float pWidth, float pHeight, ITextureRegion pTextureRegion, 
			VertexBufferObjectManager vbom, PhysicsWorld mPhysicsWorld) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, vbom);
		
		gameManager= GameManager.getInstance();
		sceneManager= SceneManager.getInstance();

		fixtureDef = PhysicsFactory.createFixtureDef(gameManager.getPlayerDensity(), 
	    		gameManager.getPlayerElasticity(), gameManager.getPlayerFriction(), true);
	    /*body = PhysicsFactory.createCircleBody(mPhysicsWorld, this, BodyType.DynamicBody, fixtureDef);
	    mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(this, this.body, true, true));*/
	    //sceneManager.getCurrentScene().attachChild(this);
	}
	
	public void initialize(Body  pBody, PhysicsWorld mPhysicsWorld) {
	    //body = PhysicsFactory.createCircleBody(mPhysicsWorld, this, BodyType.DynamicBody, fixtureDef);
		body = PhysicsFactory.createCircleBody(mPhysicsWorld, this, BodyType.StaticBody, fixtureDef);
	    mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(this, this.body, true, true));
		/*distanceJointDef.initialize(pBody, this.body, anchorA, this.body.getWorldCenter());
		distanceJointDef.length = 3.0f;
		distanceJointDef.frequencyHz = 1f;
		distanceJointDef.dampingRatio = 0.001f;
		mPhysicsWorld.createJoint(distanceJointDef);*/
	}

}

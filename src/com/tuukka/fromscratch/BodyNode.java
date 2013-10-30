package com.tuukka.fromscratch;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;

public class BodyNode extends Sprite {

	private DistanceJointDef distanceJointDef = new DistanceJointDef();

	private DistanceJoint joint;
	private Body body;
	private FixtureDef fixtureDef;

	public BodyNode(float pX, float pY, float pWidth, float pHeight,
			ITextureRegion pTextureRegion,
			ISpriteVertexBufferObject pSpriteVertexBufferObject) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pSpriteVertexBufferObject);
		
	}
	
	public void initialize(Body  pBody, Vector2 anchorA) {
		this.body.getWorldCenter();
		distanceJointDef.initialize(pBody, this.body, anchorA, this.body.getWorldCenter());
		distanceJointDef.length = 3.0f;
		distanceJointDef.frequencyHz = 1f;
		distanceJointDef.dampingRatio = 0.001f;

	}

}

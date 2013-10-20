package com.tuukka.fromscratch;


import org.andengine.extension.physics.box2d.util.Vector2Pool;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class MoveBodyTask {
	private Body pBody;
	private Vector2 position;
	private float angle;
	
	public MoveBodyTask(Body body, float x, float y) {
		pBody = body;
		angle = body.getAngle();
		position = Vector2Pool.obtain(x, y);
	}

	public MoveBodyTask(Body body, Vector2 v2) {
		angle = body.getAngle();
		pBody = body;
		position = v2;
	}
	
	public void move() {
		pBody.setTransform(position, angle);
	}
}

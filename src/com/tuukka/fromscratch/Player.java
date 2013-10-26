package com.tuukka.fromscratch;

import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Vibrator;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import com.badlogic.gdx.physics.box2d.Body;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Player extends AnimatedSprite {
	
	private Body body;
	private float health;
	private PhysicsWorld physworld; // do we need this?
	private FixtureDef playerFixtureDef;
	
	private GameManager gameManager;
	

	public Player(float x, float y, TiledTextureRegion player_region,
			VertexBufferObjectManager vbom, PhysicsWorld physicsWorld) {
		super(x, y, player_region, vbom);

		gameManager = GameManager.getInstance();
	    //playerFixtureDef = PhysicsFactory.createFixtureDef(1, 0.5f, 1.0f); // get these parameters from optionsmanager or somesuch
	    playerFixtureDef = PhysicsFactory.createFixtureDef(gameManager.getPlayerDensity(), 
	    		gameManager.getPlayerElasticity(), gameManager.getPlayerFriction());
	    body = PhysicsFactory.createCircleBody(physicsWorld, this, BodyType.DynamicBody, playerFixtureDef);
	    physicsWorld.registerPhysicsConnector(new PhysicsConnector(this, body, true, true));
	    body.setUserData("player");
	    this.setUserData("player");
		this.health = 100f;
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
		durations[0] = (long) 80;
		durations[1] = (long) 160;
		int[] pFrames = new int[2];
		pFrames[0] = 1;
		pFrames[1] = 0;
		Vibrator v = (Vibrator) ResourcesManager.getInstance().activity.getSystemService("VIBRATOR");
		this.animate(durations, pFrames, 5);
		ResourcesManager.getInstance().player_barf.stop();
		ResourcesManager.getInstance().player_eat.stop();
		ResourcesManager.getInstance().player_eat.play();
		Random randomgen = new Random();
		if (randomgen.nextInt() % 6== 0) {
			ResourcesManager.getInstance().player_barf.play();
			if (v != null) {
				long[] vibpattern = {2300, 5000};
				v.vibrate(vibpattern, -1);
			}
		}
		
	}

	public int hitWall(float impact) {
		if (impact > 20.0f) {
			long durations[] = new long[2];
			durations[0] = 400;
			durations[1] = 100;
			int[] pFrames = new int[2];
			pFrames[0] = 2;
			pFrames[1] = 0;
			this.stopAnimation();
			this.animate(durations, pFrames, 0);
			ResourcesManager.getInstance().player_eat.stop();
			this.health -= impact/4;
			if (health < 0) {
				health = 0;
				this.die();
			} else {
				ResourcesManager.getInstance().player_hitwall.play();
			}
		}
		// should hud and player use observer pattern? 
		// can they ?
		return (int)this.health;
	}

	private void die() {
		this.body.setType(BodyType.StaticBody);
		ResourcesManager.getInstance().player_barf.stop();
		ResourcesManager.getInstance().player_eat.stop();
		// sweet sound of dying
		ResourcesManager.getInstance().player_die.play();
		// animation
		long durations[] = new long[3];
		durations[0] = (long) 300;
		durations[1] = (long) 300;
		durations[2] = (long) 300;
		int[] pFrames = new int[3];
		pFrames[0] = 3;
		pFrames[1] = 4;
		pFrames[2] = 5;
		this.animate(durations, pFrames, 0);

	}
}

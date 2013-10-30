package com.tuukka.fromscratch;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
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
	private Float health;
	private FixtureDef playerFixtureDef;
	private DelegatedObservable obs;
	//possibly needed when creating bodynodes later
	private PhysicsWorld physworld; 
	private ResourcesManager resourcesManager;
	private SceneManager sceneManager;
	private GameManager gameManager;
	private List <BodyNode> bodyNodeList;

	public Player(float x, float y, TiledTextureRegion player_region,
			VertexBufferObjectManager vbom, PhysicsWorld physicsWorld) {
		super(x, y, player_region, vbom);
		
		resourcesManager = ResourcesManager.getInstance();
		sceneManager = SceneManager.getInstance();
		gameManager = GameManager.getInstance();

		physworld = physicsWorld;

	    playerFixtureDef = PhysicsFactory.createFixtureDef(gameManager.getPlayerDensity(), 
	    		gameManager.getPlayerElasticity(), gameManager.getPlayerFriction());
	    body = PhysicsFactory.createCircleBody(physicsWorld, this, BodyType.DynamicBody, playerFixtureDef);
	    physicsWorld.registerPhysicsConnector(new PhysicsConnector(this, body, true, true));
	    body.setUserData("player");
	    this.setUserData("player");
		this.health = 100f;
		this.obs = new DelegatedObservable();
	}

	public void eat() {
		addBodyNode();
		long durations[] = new long[2];
		durations[0] = (long) 80;
		durations[1] = (long) 160;
		int[] pFrames = new int[2];
		pFrames[0] = 1;
		pFrames[1] = 0;
		Vibrator v = (Vibrator) resourcesManager.activity.getSystemService("VIBRATOR");
		this.animate(durations, pFrames, 5);
		resourcesManager.player_barf.stop();
		resourcesManager.player_eat.stop();
		resourcesManager.player_eat.play();
		Random randomgen = new Random();
		if (randomgen.nextInt() % 6== 0) {
			resourcesManager.player_barf.play();
			if (v != null) {
				long[] vibpattern = {2300, 5000};
				v.vibrate(vibpattern, -1);
			}
		}
		
	}

	private void addBodyNode() {
		
	}

	public void hitWall(float impact) {
		if (impact > 20.0f) {
			long durations[] = new long[2];
			durations[0] = 400;
			durations[1] = 100;
			int[] pFrames = new int[2];
			pFrames[0] = 2;
			pFrames[1] = 0;
			this.stopAnimation();
			this.animate(durations, pFrames, 0);
			resourcesManager.player_eat.stop();
			this.health -= impact/4;
			if (health < 0) {
				obs.setChanged();
				health = (float) 0;
				obs.notifyObservers(health);
				this.die();
			} else {
				resourcesManager.player_hitwall.play();
				obs.setChanged();
				obs.notifyObservers(health);
			}
		}
	}

	private void die() {
		this.body.setType(BodyType.StaticBody);
		resourcesManager.player_barf.stop();
		resourcesManager.player_eat.stop();
		// sweet sound of dying
		resourcesManager.player_die.play();
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
	
	public synchronized void addObserver(Observer o) {
		this.obs.addObserver(o);
	}
	public synchronized void deleteObserver(Observer o) {
		this.obs.deleteObserver(o);
	}

}
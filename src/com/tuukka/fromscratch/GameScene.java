package com.tuukka.fromscratch;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.util.Vector2Pool;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;


import android.hardware.SensorManager;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.tuukka.fromscratch.SceneManager.SceneType;


public class GameScene extends BaseScene implements IAccelerationListener{
	private HUD gameHUD;
	private Text scoreText;
	private int score = 0;
	private PhysicsWorld physicsWorld;
	private Player player;
	private Pill redpill;
	private Body player_body;
	private float CAMERA_WIDTH;
	private float CAMERA_HEIGHT;
	
	public void createScene() {
		
		ResourcesManager.getInstance().loadGameResources();
		
		createBackground();
		createHUD();
	    createPhysics();
	    
	    ResourcesManager.getInstance().engine.enableAccelerationSensor(activity, this);
	    
	    CAMERA_WIDTH = ResourcesManager.getInstance().camera.getXMax();
	    CAMERA_HEIGHT = ResourcesManager.getInstance().camera.getYMax();
	    
	    //createGameOverText();

		//final FixtureDef objectFixtureDef = PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);
	    player = new Player((ResourcesManager.getInstance().camera.getXMax()/2), (ResourcesManager.getInstance().camera.getYMax()/2), resourcesManager.player_region, vbom);
	    final FixtureDef playerFixtureDef = PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);
        PhysicsFactory.createBoxBody(physicsWorld, player, BodyType.DynamicBody, playerFixtureDef).setUserData("player");
//	    locatePill();
        this.attachChild(player);
        float redpillY = (float) (Math.random() * CAMERA_HEIGHT);
        float redpillX = (float) (Math.random() * CAMERA_WIDTH);
        redpill = new Pill(redpillX, redpillY, ResourcesManager.getInstance().redpill_region, vbom);
        this.attachChild(redpill);
			
        player_body = PhysicsFactory.createCircleBody(this.physicsWorld, player, BodyType.DynamicBody, playerFixtureDef);

        this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(player, player_body, true, true));
        
        ResourcesManager.getInstance().camera.setChaseEntity(player);

		final Rectangle ground = new Rectangle(CAMERA_WIDTH / 2, 1, CAMERA_WIDTH, 2, vbom);
		final Rectangle roof = new Rectangle(CAMERA_WIDTH / 2, CAMERA_HEIGHT - 1, CAMERA_WIDTH, 2, vbom);
		final Rectangle left = new Rectangle(1, CAMERA_HEIGHT / 2, 1, CAMERA_HEIGHT, vbom);
		final Rectangle right = new Rectangle(CAMERA_WIDTH - 1, CAMERA_HEIGHT / 2, 2, CAMERA_HEIGHT, vbom);

		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0.5f, 0.5f);
		PhysicsFactory.createBoxBody(this.physicsWorld, ground, BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(this.physicsWorld, roof, BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(this.physicsWorld, left, BodyType.StaticBody, wallFixtureDef);
		PhysicsFactory.createBoxBody(this.physicsWorld, right, BodyType.StaticBody, wallFixtureDef);

		this.attachChild(ground);
		this.attachChild(roof);
		this.attachChild(left);
		this.attachChild(right);

	    
	}



	private void createPhysics() {
//		physicsWorld = new FixedStepPhysicsWorld(30, new Vector2(0,SensorManager.GRAVITY_EARTH), false);
		physicsWorld = new FixedStepPhysicsWorld(30, new Vector2(0,0), false);
	    physicsWorld.setContactListener(contactListener());
		registerUpdateHandler(physicsWorld);
		
	}

	private void createHUD() {
		gameHUD = new HUD();
		scoreText = new Text(20, 420, resourcesManager.font, "score:0123456789", 
				new TextOptions(HorizontalAlign.LEFT), vbom);

	    scoreText.setAnchorCenter(0, 0);    
		scoreText.setText("score: 0");
		gameHUD.attachChild(scoreText);
		camera.setHUD(gameHUD);
		
	}

	private void createBackground() {
		this.setBackground(new Background(Color.BLUE));
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {
		camera.setHUD(null);
		camera.setCenter(400, 240);
	
		// TODO code responsible for disposing scene
		// removing all game scene objects.
		
		camera.setChaseEntity(null);
	}
	
	
	private ContactListener contactListener()
	{
		ContactListener contactListener = new ContactListener() {
			public void beginContact(Contact contact) {
			
			
			}
			public void endContact(Contact contact) {
				
				
			}


	        @Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				// TODO Auto-generated method stub
				
			}
		};
		return contactListener;
	
	}



	@Override
	public void onAccelerationAccuracyChanged(AccelerationData pAccelerationData) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onAccelerationChanged(AccelerationData pAccelerationData) {
		final Vector2 gravity = Vector2Pool.obtain(pAccelerationData.getX(), pAccelerationData.getY());
		this.physicsWorld.setGravity(gravity);
		Vector2Pool.recycle(gravity);
	}
}

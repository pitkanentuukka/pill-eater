package com.tuukka.fromscratch;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.tuukka.fromscratch.SceneManager.SceneType;


public class GameScene extends BaseScene {
	private HUD gameHUD;
	private Text scoreText;
	private int score = 0;
	private PhysicsWorld physicsWorld;
	private Player player;
	private Sprite pill;
	
	public void createScene() {
		
		createBackground();
		createHUD();
	    createPhysics();
	    
	    //createGameOverText();
	    
		
	}

	private void createPhysics() {
		physicsWorld = new FixedStepPhysicsWorld(60, new Vector2(0,-17), false);
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
}

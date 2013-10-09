package com.tuukka.fromscratch;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.util.adt.color.Color;

import com.tuukka.fromscratch.SceneManager.SceneType;


public class GameScene extends BaseScene {
	private HUD gameHUD;
	private Text scoreText;
	private int score = 0;
	private PhysicsWorld physicsWorld;
	private Player player;
	
	public void createScene() {
		
		createBackground();
		createHUD();
	    createPhysics();
	    
	    //createGameOverText();
	    
		
	}

	private void createPhysics() {
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}

}

package com.tuukka.fromscratch;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

import com.tuukka.fromscratch.SceneManager.SceneType;

public class GameOverScene extends BaseScene implements IOnSceneTouchListener {

	private Text text;

	@Override
	public void createScene() {
		// TODO Auto-generated method stub

		this.CAMERA_HEIGHT = 480;
		this.CAMERA_WIDTH = 800;
		// TODO Auto-generated method stub
		setBackground(new Background(Color.BLACK));
		text = new Text(0,0, resourcesManager.font,"Game \nOver!", vbom);
		text.setScale(6.0f);
		//text.setSize(500, 300);
		
	    //attachChild(new Text(400, 240, resourcesManager.font, "Loading...", vbom));
		this.attachChild(text);
		//text.setPosition(CAMERA_WIDTH/2-(text.getWidth()), CAMERA_HEIGHT/2-(text.getHeight()));
		text.setPosition(CAMERA_WIDTH/2, CAMERA_HEIGHT/2);
		setOnSceneTouchListener(this);

	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub

	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneManager.SceneType.SCENE_GAMEOVER;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		this.disposeScene();
		sceneManager.createGameScene();
		return true;
	}

}

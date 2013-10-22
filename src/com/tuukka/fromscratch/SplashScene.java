package com.tuukka.fromscratch;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;

import com.tuukka.fromscratch.SceneManager.SceneType;

public class SplashScene extends BaseScene {

	private Text text;

	@Override
	public void createScene() {
		this.CAMERA_HEIGHT = 480;
		this.CAMERA_WIDTH = 800;
		// TODO Auto-generated method stub
		setBackground(new Background(Color.WHITE));
		text = new Text(0,0, ResourcesManager.getInstance().font,"loading", vbom);
	    //attachChild(new Text(400, 240, resourcesManager.font, "Loading...", vbom));
		this.attachChild(text);
		text.setPosition(CAMERA_WIDTH/2-(text.getWidth()), CAMERA_HEIGHT/2-(text.getHeight()));

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

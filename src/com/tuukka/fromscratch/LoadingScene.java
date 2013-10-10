package com.tuukka.fromscratch;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;

import com.tuukka.fromscratch.SceneManager.SceneType;

public class LoadingScene extends BaseScene {

	@Override
	public void createScene() {

		setBackground(new Background(Color.WHITE));
	    attachChild(new Text(400, 240, resourcesManager.font, "Loading...", vbom));
	}

	public void onBackKeyPressed() {
		return;
	}

	public SceneType getSceneType() {
		return SceneType.SCENE_LOADING;
	}

	public void disposeScene() {

	}

}

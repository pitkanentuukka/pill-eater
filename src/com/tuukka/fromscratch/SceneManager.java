package com.tuukka.fromscratch;

import org.andengine.entity.scene.Scene;

public class SceneManager {
	
	private static SceneManager INSTANCE= new SceneManager();
	
	private Scene loadingScene;
	private Scene gameScene;
	
	public enum SceneType {
		SCENE_LOADING,
		SCENE_GAME,
	}
	
	private SceneManager() {
		// private constructor
	}

	public static SceneManager getInstance() {
		return INSTANCE;
	}


}

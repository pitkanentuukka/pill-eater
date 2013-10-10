package com.tuukka.fromscratch;

import org.andengine.entity.scene.Scene;

import com.tuukka.fromscratch.SceneManager.SceneType;

public class SceneManager {
	
	private static SceneManager INSTANCE= new SceneManager();
	
	private BaseScene loadingScene;
	private BaseScene gameScene;
	
    private BaseScene currentScene;

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


	public void createLoadingScene() {
		// TODO Auto-generated method stub
		
	}

	private void createGameScene() {
		// TODO Auto-generated method stub
	}

	public BaseScene getCurrentScene() {
		// TODO Auto-generated method stub

		return currentScene;
	}



}

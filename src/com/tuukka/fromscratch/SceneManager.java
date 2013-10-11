package com.tuukka.fromscratch;

import org.andengine.entity.scene.Scene;

import com.tuukka.fromscratch.SceneManager.SceneType;

public class SceneManager {
	
	private static SceneManager INSTANCE= new SceneManager();
	
	private BaseScene loadingScene;
	private BaseScene gameScene;
	
    private BaseScene currentScene;

	private SceneType currentSceneType;

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
		loadingScene = new LoadingScene();
		currentScene = loadingScene;
		currentSceneType = currentScene.getSceneType();
	}

	private void createGameScene() {
		// TODO Auto-generated method stub
		gameScene = new GameScene();
		currentScene = gameScene;
		currentSceneType = currentScene.getSceneType();
	}

	public BaseScene getCurrentScene() {
		return currentScene;
	}

	public SceneType getCurrentSceneType() {
		return currentScene.getSceneType();
	}

}

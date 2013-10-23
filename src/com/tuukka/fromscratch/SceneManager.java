package com.tuukka.fromscratch;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;


import com.tuukka.fromscratch.SceneManager.SceneType;

public class SceneManager {
	
	private static SceneManager INSTANCE= new SceneManager();
	
	private Engine engine = ResourcesManager.getInstance().engine;
	private BaseScene loadingScene;
	private BaseScene gameScene;
	private BaseScene splashScene;
	private BaseScene gameOverScene;
	
    private BaseScene currentScene;

	private SceneType currentSceneType;

	public enum SceneType {
		SCENE_LOADING,
		SCENE_GAME,
		SCENE_SPLASH,
		SCENE_GAMEOVER,
	}
	
	private SceneManager() {
		// private constructor
	}

	public static SceneManager getInstance() {
		return INSTANCE;
	}

	public void setScene(BaseScene scene)
	{
		engine.setScene(scene);
		currentScene = scene;
		currentSceneType = scene.getSceneType();
	}


	public void createLoadingScene() {
		// TODO Auto-generated method stub
		loadingScene = new LoadingScene();
		currentScene = loadingScene;
		currentSceneType = currentScene.getSceneType();
	}

	public void createGameScene() {
		// TODO Auto-generated method stub
		gameScene = new GameScene();
		currentScene = gameScene;
		setScene(gameScene);
		currentSceneType = currentScene.getSceneType();
	}
	public void createOverGameScene() {
		// TODO Auto-generated method stub
		gameScene.disposeScene();
		gameOverScene = new GameOverScene();
		currentScene = gameOverScene;
		setScene(gameOverScene);
		currentSceneType = currentScene.getSceneType();
	}
	public BaseScene getCurrentScene() {
		return currentScene;
	}

	public SceneType getCurrentSceneType() {
		return currentScene.getSceneType();
	}

	public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback) {
		// TODO Auto-generated method stub
		
		ResourcesManager.getInstance().loadSplashScreen();
		splashScene = new SplashScene();
		currentScene = splashScene;
		pOnCreateSceneCallback.onCreateSceneFinished(splashScene);

	}

}

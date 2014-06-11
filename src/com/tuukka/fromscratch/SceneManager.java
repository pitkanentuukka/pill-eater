package com.tuukka.fromscratch;

import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

public class SceneManager {
	
	private static SceneManager INSTANCE= new SceneManager();
	
	private Engine engine = ResourcesManager.getInstance().engine;
	private BaseScene loadingScene;
	private BaseScene gameScene;
	private BaseScene splashScene;
	private BaseScene menuScene;
	private BaseScene gameOverScene;
	
    private BaseScene currentScene;

	@SuppressWarnings("unused")
	private SceneType currentSceneType;

	public enum SceneType {
		SCENE_LOADING,
		SCENE_MENU,
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
	}
	public void createOverGameScene() {
		// TODO Auto-generated method stub
		gameScene.disposeScene();
		gameOverScene = new GameOverScene();
		currentScene = gameOverScene;
		setScene(gameOverScene);
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

	public void createMenuScene() {
		// TODO Auto-generated method stub
		menuScene = new MenuScene();
		currentScene = menuScene;
		setScene(currentScene);
	}

}

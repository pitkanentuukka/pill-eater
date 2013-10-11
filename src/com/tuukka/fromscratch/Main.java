package com.tuukka.fromscratch;

import java.io.IOException;

import org.andengine.engine.Engine;
import org.andengine.engine.FixedStepEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.util.Log;

public class Main extends BaseGameActivity {
	
	
	private static final float WIDTH = 800;
	private static final float HEIGHT = 480;
	private Camera camera;
	private BaseScene scene; 
	private BitmapTextureAtlas texture;
/*	
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub
		camera = new Camera(0, 0, WIDTH, HEIGHT);
		EngineOptions engineoptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
		engineoptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		return engineoptions;
	}

	protected void onCreateResources() throws IOException {
		// TODO Auto-generated method stub
		ResourcesManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager());
		ResourcesManager.getInstance().loadGameResources();
		Log.i("Main", "loaded game resources");
		SceneManager.getInstance().createGameScene();
	}

	protected Scene onCreateScene() {
		SceneManager.getInstance().createLoadingScene();
		Log.i("Main", "scenemanager created loadingscene");
		return SceneManager.getInstance().getCurrentScene();
	}
	
	public Engine onCreateEngine(EngineOptions pEngineOptions) {
	  // Create a fixed step engine updating at 60 steps per second
		Log.i("Main", "created fixedstepengine");
	    return new FixedStepEngine(pEngineOptions, 60);
	  }

	*/
	@Override
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub
		camera = new Camera(0, 0, WIDTH, HEIGHT);
		EngineOptions engineoptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
		engineoptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		return engineoptions;
	}
	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws IOException {
		// TODO Auto-generated method stub
		
		ResourcesManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager());
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}
	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws IOException {
		// TODO Auto-generated method stub
		SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
		
	}
	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback)
			throws IOException {
		// TODO Auto-generated method stub
		
	}


	// ===========================================================
	// Inner and Anonymous Classes
	// =========================================================== 

}

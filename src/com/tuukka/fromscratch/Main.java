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
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class Main extends SimpleBaseGameActivity {
	
	
	private static final float WIDTH = 800;
	private static final float HEIGHT = 480;
	private Camera camera;
	private BaseScene scene; 
	private BitmapTextureAtlas texture;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub
		camera = new Camera(0, 0, WIDTH, HEIGHT);
		EngineOptions engineoptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), camera);
		engineoptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
		return engineoptions;
	}

	@Override
	protected void onCreateResources() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Scene onCreateScene() {
		SceneManager.getInstance().createLoadingScene();
		return SceneManager.getInstance().getCurrentScene();
	}
	
	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) {
	  // Create a fixed step engine updating at 60 steps per second
	    return new FixedStepEngine(pEngineOptions, 60);
	  }

	


	// ===========================================================
	// Inner and Anonymous Classes
	// =========================================================== 

}

package com.tuukka.fromscratch;

import java.io.IOException;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.IBackground;
import org.andengine.entity.util.FPSLogger;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.adt.color.Color;

public class Main extends BaseGameActivity {
	
	// ===========================================================
	// Constants
	// ===========================================================
	 
	static final int CAMERA_WIDTH = 480;
	static final int CAMERA_HEIGHT = 320;
	private static final String TAG = "AndEngineTest";
	
	// ===========================================================
	// Fields
	// ===========================================================
	private ZoomCamera mCamera;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	 
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	 
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	 
	@Override
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws IOException {
		// TODO Auto-generaMainted method stub
		
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback)
			throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
    public Engine onLoadEngine() {
    	this.mCamera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
    	return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));

    }
    
    public Scene onLoadScene() {
    	this.mEngine.registerUpdateHandler(new FPSLogger());
    	 
    	final Scene scene = new Scene(1);
    	scene.setBackground((IBackground) new Color(0, 0, 0.8784f));
    	 
    	return scene;
    }


	// ===========================================================
	// Inner and Anonymous Classes
	// =========================================================== 

}

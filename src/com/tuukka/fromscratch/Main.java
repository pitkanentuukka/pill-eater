package com.tuukka.fromscratch;

import java.io.IOException;

import org.andengine.engine.Engine;
import org.andengine.engine.FixedStepEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.IResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.IBackground;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.Texture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.color.Color;

public class Main extends SimpleBaseGameActivity {
	
	
	private static final float WIDTH = 800;
	private static final float HEIGHT = 480;
	private Camera camera;
	private Scene scene; 
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
		// TODO Auto-generated method stub
		scene = new Scene();
		
		return scene;		
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

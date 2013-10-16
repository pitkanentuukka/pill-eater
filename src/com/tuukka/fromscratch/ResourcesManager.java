package com.tuukka.fromscratch;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.adt.color.Color;

public class ResourcesManager {
	
	private static final ResourcesManager INSTANCE = new ResourcesManager();

	public TextureRegion player_region;
	//public TextureRegion redpill_region;

	public BaseGameActivity activity;
	public Engine engine;
	public Camera camera;
    public VertexBufferObjectManager vbom;
    public Font font;

    // textures
    public ITextureRegion banana;
    public ITextureRegion pacman;
    
	public BuildableBitmapTextureAtlas gameTextureAtlas;
    
	private ResourcesManager() {
		// private constructor
	}
	
	public static ResourcesManager getInstance() {
		return INSTANCE;
		
	}
	
	
    public static void prepareManager(Engine engine, BaseGameActivity activity, Camera camera, VertexBufferObjectManager vbom) 
    {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
    	getInstance().vbom = vbom;
    }
    public void loadGameResources()
    {
    	loadGameGraphics();
    	loadGameAudio();
    }

	private void loadGameAudio() {
		// TODO Auto-generated method stub
		
	}

	private void loadGameFonts() {
		// TODO Auto-generated method stub

		FontFactory.setAssetBasePath("font/");
		final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		//font = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "font.ttf", 50, true, Color.WHITE, 2, Color.BLACK);
		font = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "font.ttf", 50, true, 2 );
		font.load();
		
	}


    /*private void loadGameGraphics()
    {
    	
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
   	    gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
   	    //player_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "pacman.png", 2, 1);
   	    player_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "player_grin.png");
   	    redpill_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "redpill.png");
   	   
    }*/
	private void loadGameGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		//BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(engine.getTextureManager(), 167, 128);
		gameTextureAtlas = new BuildableBitmapTextureAtlas(engine.getTextureManager(), 167, 128);
		this.player_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "player_mouth_closed.png"); 
		//this.redpill_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "redpill.png");
		
	}

	public void loadSplashScreen() {
		// TODO Auto-generated method stub
		
    	loadGameFonts();
	}
	
}

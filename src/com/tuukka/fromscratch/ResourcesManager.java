package com.tuukka.fromscratch;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

public class ResourcesManager {
	
	private static final ResourcesManager INSTANCE = new ResourcesManager();

	public ITiledTextureRegion player_region;
	public TextureRegion redpill_region;

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
    	loadGameFonts();
    	loadGameAudio();
    }

	private void loadGameAudio() {
		// TODO Auto-generated method stub
		
	}

	private void loadGameFonts() {
		// TODO Auto-generated method stub
		
	}


    private void loadGameGraphics()
    {
    	
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
   	    gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
   	    player_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "pacman.png", 2, 1);
   	    redpill_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "redpill.png");
   	   
    }
	
}

package com.tuukka.fromscratch;

import java.io.IOException;

import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;

public class ResourcesManager {
	
	private static final ResourcesManager INSTANCE = new ResourcesManager();

	public TiledTextureRegion player_region;
	public TextureRegion redpill_region;
	
	public Sound player_eat;
	public Sound player_barf;
	public Sound player_hitwall;
	public BaseGameActivity activity;
	public Engine engine;
	public Camera camera;
    public VertexBufferObjectManager vbom;
    public Font font;

    // textures
    
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
    	loadGameSounds();
    }


	private void loadGameSounds() {
		// TODO Auto-generated method stub
		
		SoundFactory.setAssetBasePath("sfx/");
		try {
			player_eat = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "eat.wav");
			player_barf = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "barf.wav");
			player_hitwall = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "player_hitwall2.wav");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private void loadGameFonts() {
		// TODO Auto-generated method stub

		FontFactory.setAssetBasePath("font/");
		final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);

		//font = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "font.ttf", 50, true, Color.WHITE, 2, Color.BLACK);
		font = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "font.ttf", 32, true, 100);
		font.load();
		
	}


	private void loadGameGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		//BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(engine.getTextureManager(), 167, 128);
		gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		this.player_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "player_tiled_2.png", 3, 1); 
		this.redpill_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "redpill.png");
		
		try {
			this.gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.gameTextureAtlas.load();
		}
		catch (final TextureAtlasBuilderException e)
    	{
			Debug.e(e);
		}
		/*
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
        gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        
       	platform1_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "platform1.png");
       	platform2_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "platform2.png");
       	platform3_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "platform3.png");
        coin_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "coin.png");
        player_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "player.png", 3, 1);
        
        complete_window_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "levelCompleteWindow.png");
        complete_stars_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "star.png", 2, 1);

    	try 
    	{
			this.gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.gameTextureAtlas.load();
		} 
    	catch (final TextureAtlasBuilderException e)
    	{
			Debug.e(e);
		}
		 */
		
	}

	public void loadSplashScreen() {
		// TODO Auto-generated method stub
    	loadGameFonts();
		
	}
	
}

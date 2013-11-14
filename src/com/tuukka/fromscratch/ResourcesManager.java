package com.tuukka.fromscratch;

import java.io.IOException;

import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
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
	public TextureRegion tile_region;

	public Sound player_eat;
	public Sound player_barf;
	public Sound player_hitwall;
	public Sound player_die;
	public BaseGameActivity activity;
	public Engine engine;
	public BoundCamera camera;
    public VertexBufferObjectManager vbom;
    public Font font;

    // textures
    
	public BuildableBitmapTextureAtlas gameTextureAtlas;

	public TextureRegion menu_background_region;


    
	private ResourcesManager() {
		// private constructor
	}
	
	public static ResourcesManager getInstance() {
		return INSTANCE;
		
	}
	
	
    public static void prepareManager(Engine engine, BaseGameActivity activity, BoundCamera camera, VertexBufferObjectManager vbom) 
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
			player_eat = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "eat3.wav");
			player_barf = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "barf.wav");
			player_hitwall = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "player_hitwall2.wav");
			player_die = SoundFactory.createSoundFromAsset(activity.getSoundManager(), activity, "die3.wav");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private void loadGameFonts() {

		FontFactory.setAssetBasePath("font/");
		final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 512, 512, TextureOptions.BILINEAR);
		font = FontFactory.createFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "StayPuft.ttf", 32, true, android.graphics.Color.RED);
		font.load();
		
	}


	private void loadGameGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		//BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(engine.getTextureManager(), 167, 128);
		gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
		this.player_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "player_tiled_3.png", 3, 2); 
		this.redpill_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "redpill.png");
		this.tile_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "tile.png");
		this.menu_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "menubackground.png");		
		try {
			this.gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
			this.gameTextureAtlas.load();
		}
		catch (final TextureAtlasBuilderException e)
    	{
			Debug.e(e);
		}
	
		
	}

	public void loadSplashScreen() {
		// TODO Auto-generated method stub
    	loadGameFonts();
		
	}
	
}

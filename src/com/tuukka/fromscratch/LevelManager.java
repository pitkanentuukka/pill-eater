package com.tuukka.fromscratch;

import java.io.IOException;

import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.SAXUtils;
import org.andengine.util.level.EntityLoader;
import org.andengine.util.level.constants.LevelConstants;
import org.andengine.util.level.simple.SimpleLevelLoader;

import com.badlogic.gdx.physics.box2d.FixtureDef;

import android.app.Activity;

public class LevelManager {

	private static final LevelManager INSTANCE = new LevelManager();
	
	
	private String[] levelList;
	private Activity activity;
	private int currentLevel = 0;
	private BoundCamera camera;
	private VertexBufferObjectManager vbom;
	
	private SceneManager sceneManager;



	public static LevelManager getInstance() {
		return INSTANCE;
		
	}
	
	
	
	public void prepareManager(Activity activity, VertexBufferObjectManager vbom, BoundCamera camera) {
		this.activity = activity;
		this.vbom = vbom;
		this.camera = camera;

		this.sceneManager = SceneManager.getInstance();
		
		
	}
	
	public void loadLevels() {
				try {
			levelList = activity.getAssets().list("level");
			//loadLevel(currentLevel);
			//System.out.println(levelList.length);
			/*for (int i = 0; i < levelList.length; i++)
				Log.d("levelList: ", String.valueOf(i) + ": " + levelList[i]);*/
			//Log.d("levelList:", String.valueOf(levelList.length));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadLevel(int levelID) {

		final FixtureDef WALL_FIXTURE_DEF = PhysicsFactory.createFixtureDef(0, 0.01f, 0.5f);
	    final SimpleLevelLoader levelLoader = new SimpleLevelLoader(vbom);
	    
		levelLoader.registerEntityLoader(new EntityLoader<SimpleLevelEntityLoaderData>(LevelConstants.TAG_LEVEL)
				{

					@Override
					public IEntity onLoadEntity(String pEntityName,
							IEntity pParent, Attributes pAttributes,
							SimpleLevelEntityLoaderData pEntityLoaderData)
							throws IOException {
			            final int width = SAXUtils.getIntAttributeOrThrow(pAttributes, LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH);
			            final int height = SAXUtils.getIntAttributeOrThrow(pAttributes, LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT);
			            
			            camera.setBounds(0, 0, width, height); // here we set camera bounds
			            camera.setBoundsEnabled(true);
			            //return GameScene.this;
			            return sceneManager.getCurrentScene();
					}
			
				});
		 levelLoader.registerEntityLoader(new EntityLoader<SimpleLevelEntityLoaderData>(TAG_ENTITY) {
				        public IEntity onLoadEntity(final String pEntityName, final IEntity pParent, final Attributes pAttributes, final SimpleLevelEntityLoaderData pSimpleLevelEntityLoaderData) throws IOException {
				            final int x = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_X);
				            final int y = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_Y);
				            final String type = SAXUtils.getAttributeOrThrow(pAttributes, TAG_ENTITY_ATTRIBUTE_TYPE);
				            
				            final Sprite levelObject;
				            
				            if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_TILE)) {
				                levelObject = new Sprite(x, y, resourcesManager.tile_region, vbom);
				                Body body  = PhysicsFactory.createBoxBody(physicsWorld, levelObject, BodyType.StaticBody, WALL_FIXTURE_DEF);
				                body.setUserData("wall");
				            } else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PILL)) {
				            	pillCount++;
				                levelObject = new Sprite(x, y, resourcesManager.redpill_region, vbom) {
									@Override
				                    protected void onManagedUpdate(float pSecondsElapsed) 
				                    {
				                        super.onManagedUpdate(pSecondsElapsed);
				                        if (player.collidesWith(this))
				                        {
				                        	GameScene.this.score++;
        									GameScene.this.scoreText.setText("score: " + score);
        									player.eat();
				                            this.setVisible(false);
				                            this.setIgnoreUpdate(true);
				                            pillCount--;
				                            if (pillCount == 0) {
				                            	exit.enable(player, GameScene.this);
				                            }
				                        }
				                    }
				                };
				            	
				            } else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_EXIT)) {
				                exit = new Exit(x, y, resourcesManager.exit_region, vbom);
				                levelObject = exit;
				            } else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLAYER)) {
				            	if (player == null) {
					            	player = new Player(x, y, resourcesManager.player_region, vbom, physicsWorld);
					            	player.addObserver(GameScene.this);
				            	}
				            	camera.setChaseEntity(player);
				                levelObject = player;
				            } else {
				                throw new IllegalArgumentException();
				            }
				            levelObject.setCullingEnabled(true);
				            return levelObject;
				        }
				    });
				    levelLoader.loadLevelFromAsset(activity.getAssets(), "level/"+levelList[levelID]);
	}
		
		
	
}

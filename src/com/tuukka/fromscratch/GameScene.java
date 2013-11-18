package com.tuukka.fromscratch;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.util.SAXUtils;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.level.EntityLoader;
import org.andengine.util.level.constants.LevelConstants;
import org.andengine.util.level.simple.SimpleLevelEntityLoaderData;
import org.andengine.util.level.simple.SimpleLevelLoader;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.util.Vector2Pool;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.xml.sax.Attributes;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.tuukka.fromscratch.SceneManager.SceneType;


public class GameScene extends BaseScene implements IAccelerationListener, Observer{
	
	// these are for level loading
	private static final String TAG_ENTITY = "entity";
	private static final String TAG_ENTITY_ATTRIBUTE_X = "x";
	private static final String TAG_ENTITY_ATTRIBUTE_Y = "y";
	private static final String TAG_ENTITY_ATTRIBUTE_TYPE = "type";

	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_TILE = "tile";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PILL = "pill";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLAYER = "player";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_EXIT = "exit";


	private HUD gameHUD;
	private Text scoreText;
	private Text healthText;
	private int score = 0;
	private int pillCount;
	private PhysicsWorld physicsWorld;
	private Player player;
	
	private Exit exit;
	
	public void createScene() {
		
		resourcesManager.loadGameResources();
		
		createBackground();
		createHUD();
	    createPhysics();
	    
	    resourcesManager.engine.enableAccelerationSensor(activity, this);
	    
	    CAMERA_WIDTH = resourcesManager.camera.getXMax();
	    CAMERA_HEIGHT = resourcesManager.camera.getYMax();
	    
	    this.pillCount = 0; 
	    

	    loadLevel(1);
	}



	private void createPhysics() {
		physicsWorld = new FixedStepPhysicsWorld(30, new Vector2(0,0), false);
	    physicsWorld.setContactListener(contactListener());
		registerUpdateHandler(physicsWorld);
		
	}

	private void createHUD() {
		gameHUD = new HUD();
		gameHUD.setColor(Color.BLACK);
		/*gameHUD.setX(50);
		gameHUD.setY(50);*/
		gameHUD.setPosition(80, 40);
		scoreText = new Text(20, 420, resourcesManager.font, "score:0123456789", 
				new TextOptions(HorizontalAlign.LEFT), vbom);

		scoreText.setText("score: 0");
		scoreText.setScale(2);
		scoreText.setPosition(scoreText.getWidth()/2, scoreText.getHeight()/2);
		gameHUD.attachChild(scoreText);

		healthText = new Text(20, 420, resourcesManager.font, "health:0123456789", 
				new TextOptions(HorizontalAlign.LEFT), vbom);
		healthText.setText("health:100");
		healthText.setScale(2);
		healthText.setPosition( healthText.getWidth()/2, scoreText.getHeight() +healthText.getHeight()/2);
		gameHUD.attachChild(healthText);
		

		camera.setHUD(gameHUD);
		
	}

	private void createBackground() {
		this.setBackground(new Background(Color.BLUE));
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {
		camera.setHUD(null);
		camera.setCenter(400, 240);
	
		// TODO code responsible for disposing scene
		// removing all game scene objects.
		
		camera.setChaseEntity(null);
	}
	
	
	private ContactListener contactListener()
	{
		ContactListener contactListener = new ContactListener() {
			public void beginContact(Contact contact) {
	            

			}

			public void endContact(Contact contact) {
				
				
			}


	        @Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
	            final Fixture x1 = contact.getFixtureA();
	            final Fixture x2 = contact.getFixtureB();
				// TODO Auto-generated method stub
	            if (x1.getBody().getUserData() == "wall" && x2.getBody().getUserData() == "player") {
	            	float firsthit = impulse.getNormalImpulses()[0];
	            	player.hitWall(firsthit);
	            } else if (x1.getBody().getUserData() == "player" && x2.getBody().getUserData() == "wall") {
	            	float firsthit = impulse.getNormalImpulses()[0];
	            	player.hitWall(firsthit);
	            	
	            }
				
			}
		};
		return contactListener;
	
	}



	@Override
	public void onAccelerationAccuracyChanged(AccelerationData pAccelerationData) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onAccelerationChanged(AccelerationData pAccelerationData) {
		final Vector2 gravity = Vector2Pool.obtain(pAccelerationData.getX(), pAccelerationData.getY());
		this.physicsWorld.setGravity(gravity);
		Vector2Pool.recycle(gravity);
	}

	
	public void gameOver() {
		TimerHandler gameOverTimeHandler;
		resourcesManager.engine.registerUpdateHandler(gameOverTimeHandler = new TimerHandler(3, new ITimerCallback(){
			public void onTimePassed(final TimerHandler pTimerHandler) {
				disposeScene();
				sceneManager.createOverGameScene();
				
			}
		}));
		
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if (arg1 instanceof Float) {
			String health = String.format("%.0f", arg1);
			this.healthText.setText("health: " + health);
			if(0 >= ((Float) arg1).compareTo((float)(0))) {
				this.gameOver();
			}
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
			            return GameScene.this;
					}
			
				});
		 levelLoader.registerEntityLoader(new EntityLoader<SimpleLevelEntityLoaderData>(TAG_ENTITY)
				    {
				        public IEntity onLoadEntity(final String pEntityName, final IEntity pParent, final Attributes pAttributes, final SimpleLevelEntityLoaderData pSimpleLevelEntityLoaderData) throws IOException
				        {
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
				            	player = new Player(x, y, resourcesManager.player_region, vbom, physicsWorld);
				            	player.addObserver(GameScene.this);
				            	camera.setChaseEntity(player);
				                levelObject = player;
				            } else {
				                throw new IllegalArgumentException();
				            }
				            levelObject.setCullingEnabled(true);
				            return levelObject;
				        }
				    });
				    levelLoader.loadLevelFromAsset(activity.getAssets(), "level/" + levelID + ".lvl");
	}

	public void levelComplete() {
		// TODO Auto-generated method stub
		this.gameOver();
		
	}


}
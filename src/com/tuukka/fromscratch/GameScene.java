package com.tuukka.fromscratch;

import java.util.LinkedList;
import java.util.List;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.util.Vector2Pool;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;


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


public class GameScene extends BaseScene implements IAccelerationListener{
	private HUD gameHUD;
	private Text scoreText;
	private Text healthText;
	private int score = 0;
	private PhysicsWorld physicsWorld;
	private Player player;
	private Pill redpill;
	private Body player_body;
	private Body redpill_body;
	
	private List<Task> taskList;
	private Body groundbody;
	private Body roofbody;
	private Body leftwallbody;
	private Body rightwallbody;
	
	public void createScene() {
		
		resourcesManager.loadGameResources();
		
		createBackground();
		createHUD();
	    createPhysics();
	    
	    resourcesManager.engine.enableAccelerationSensor(activity, this);
	    
	    CAMERA_WIDTH = resourcesManager.camera.getXMax();
	    CAMERA_HEIGHT = resourcesManager.camera.getYMax();
	    
	    createPlayer();
	    
	    createPill();
	    
	    createBounds();

		
		this.taskList = new LinkedList();
		
		this.registerUpdateHandler(new IUpdateHandler()
	    {


			@Override
	        public void onUpdate(float pSecondsElapsed) {
	            if(!taskList.isEmpty())
	            {
	                for(int i = 0; i < taskList.size(); i++)
	                {
	                    ((Task) taskList.get(i)).run();
	                }
	                taskList.clear();
	            }

	        }

	        public void reset() {
	            // TODO Auto-generated method stub

	        }
	    });
	    
	}


	private void createBounds() {
        // create bounds
		final Rectangle ground = new Rectangle(CAMERA_WIDTH / 2, 1, CAMERA_WIDTH, 2, vbom);
		final Rectangle roof = new Rectangle(CAMERA_WIDTH / 2, CAMERA_HEIGHT - 1, CAMERA_WIDTH, 2, vbom);
		final Rectangle left = new Rectangle(1, CAMERA_HEIGHT / 2, 1, CAMERA_HEIGHT, vbom);
		final Rectangle right = new Rectangle(CAMERA_WIDTH - 1, CAMERA_HEIGHT / 2, 2, CAMERA_HEIGHT, vbom);

		// final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0.5f, 0.5f);
		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0.0f, 0.0f, 1.5f);
		groundbody = PhysicsFactory.createBoxBody(this.physicsWorld, ground, BodyType.StaticBody, wallFixtureDef);
		roofbody = PhysicsFactory.createBoxBody(this.physicsWorld, roof, BodyType.StaticBody, wallFixtureDef);
		leftwallbody = PhysicsFactory.createBoxBody(this.physicsWorld, left, BodyType.StaticBody, wallFixtureDef);
		rightwallbody = PhysicsFactory.createBoxBody(this.physicsWorld, right, BodyType.StaticBody, wallFixtureDef);
		
		groundbody.setUserData("wall");
		roofbody.setUserData("wall");
		rightwallbody.setUserData("wall");
		leftwallbody.setUserData("wall");

		this.attachChild(ground);
		this.attachChild(roof);
		this.attachChild(left);
		this.attachChild(right);
	}


	private void createPill() {
        // create pill
        float redpillY = (float) (Math.random() * CAMERA_HEIGHT);
        float redpillX = (float) (Math.random() * CAMERA_WIDTH);
        redpill = new Pill(redpillX, redpillY, resourcesManager.redpill_region, vbom);
	    final FixtureDef redpillFixtureDef = PhysicsFactory.createFixtureDef(0, 0.0f, 0.0f);
        redpill_body = PhysicsFactory.createCircleBody(this.physicsWorld, redpill, BodyType.StaticBody, redpillFixtureDef);
        redpill_body.setUserData("redpill");
        this.physicsWorld.registerPhysicsConnector(new PhysicsConnector(redpill, redpill_body, true, true));
        redpill.setUserData("redpill");
        this.attachChild(redpill);
			
        
		
	}


	private void createPlayer() {
	    // create player
	    player = new Player((resourcesManager.camera.getXMax()/2), (resourcesManager.camera.getYMax()/2), resourcesManager.player_region, vbom, physicsWorld);
        this.attachChild(player);
		
	}



	private void createPhysics() {
//		physicsWorld = new FixedStepPhysicsWorld(30, new Vector2(0,SensorManager.GRAVITY_EARTH), false);
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
	            final Fixture x1 = contact.getFixtureA();
	            final Fixture x2 = contact.getFixtureB();
	            
	            if (x1.getBody().getUserData() == "redpill" && x2.getBody().getUserData() == "player") {
	            	playerEatsAPill();
	            } else if (x1.getBody().getUserData() == "player" && x2.getBody().getUserData() == "redpill") {
	            	playerEatsAPill();
	            	
	            }
	
			
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
	            	playerHitsWall(firsthit);
	            } else if (x1.getBody().getUserData() == "player" && x2.getBody().getUserData() == "wall") {
	            	float firsthit = impulse.getNormalImpulses()[0];
	            	playerHitsWall(firsthit);
	            	
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
	private void playerEatsAPill() {
		/* the minx, miny is width / 2, height / 2
		 * the maxx, maxy is camwidth - width/2, camheight - height/2
		 */
        final float widthD2 = redpill.getWidth() / 2;
        final float heightD2 = redpill.getHeight() / 2;
        float minY = widthD2;
        float minX = heightD2;
        // because width/2 is added to the random number, the actual max is camwidth-pillwidth
        float maxY = CAMERA_HEIGHT - redpill.getHeight();
        float maxX = CAMERA_WIDTH - redpill.getWidth();
        float y = minY + (float)Math.random() * maxY;
        float x = widthD2+(float)Math.random() * maxX;
        
        y /=32.0f;
        x /=32.0f;
        taskList.add(new MoveBodyTask(redpill_body, x, y));
        this.score++;
        this.scoreText.setText("score: " + score);
        
        player.eat();
	}		
	private void playerHitsWall(float firsthit) {
				// TODO Auto-generated method stub
		int health = player.hitWall(firsthit);
		if (health > 0) {
		this.healthText.setText("health: " +health );
		} else {
			gameOver();
		}
	}
	
	private void gameOver() {
		this.healthText.setText("health: " +0 );
		TimerHandler gameOverTimeHandler;
		resourcesManager.engine.registerUpdateHandler(gameOverTimeHandler = new TimerHandler(3, new ITimerCallback(){
			public void onTimePassed(final TimerHandler pTimerHandler) {
				disposeScene();
				sceneManager.createOverGameScene();
				
			}
		}));
		
	}
}

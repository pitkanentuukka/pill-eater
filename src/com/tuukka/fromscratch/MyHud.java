package com.tuukka.fromscratch;

import java.util.Observable;
import java.util.Observer;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.adt.align.HorizontalAlign;

public class MyHud extends HUD implements Observer{
	
	private ResourcesManager resourcesManager;
	private Text scoreText;
	private Text healthText;

	public MyHud() {
		this.setPosition(80, 40);
		resourcesManager = ResourcesManager.getInstance();
		scoreText = new Text(20, 420, resourcesManager.font, (CharSequence)"score:0123456789", 
				new TextOptions(HorizontalAlign.LEFT), resourcesManager.vbom);

		scoreText.setText("score: 0");
		scoreText.setScale(2);
		scoreText.setPosition(scoreText.getWidth()/2, scoreText.getHeight()/2);
		this.attachChild(scoreText);

		healthText = new Text(20, 420, resourcesManager.font, "health:0123456789", 
				new TextOptions(HorizontalAlign.LEFT), resourcesManager.vbom);
		healthText.setText("health:100");
		healthText.setScale(2);
		healthText.setPosition( healthText.getWidth()/2, scoreText.getHeight() +healthText.getHeight()/2);
		this.attachChild(healthText);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if (arg1 instanceof Float) {
			this.healthText.setText(arg1.toString());
			
		}
		
	}

}

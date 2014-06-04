package com.tuukka.fromscratch;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Pill extends Sprite {
	private Player player; 
	private Exit exit;

    public Pill(int x, int y, TextureRegion redpill_region,
			VertexBufferObjectManager vbom) {
    	super(x, y, redpill_region, vbom);
	}
    
    
    
    
    public void setPlayerAndExit(Player player, Exit exit) {
    	this.player = player;
    	this.exit = exit;
    }

	protected void onManagedUpdate(float pSecondsElapsed) 
    {
        super.onManagedUpdate(pSecondsElapsed);
        if (player.collidesWith(this))
        {
        	/**
        	 * who's actually keeping score?
        	 * it probably shouldn't be gamescene!
        	 * Shouldn't most of this be in the level class?
        	 */
        	
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


}

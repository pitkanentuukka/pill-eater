package com.tuukka.fromscratch;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


public class Exit extends AnimatedSprite {
	private boolean isEnabled;

	private GameScene scene;
	private Player player;

	public Exit(int x, int y, TiledTextureRegion exit_region,
			VertexBufferObjectManager vbom) {
		super(x, y, exit_region, vbom);
		this.isEnabled = false;
	}

	public void enable(Player pPlayer, GameScene pScene) {
		this.player = pPlayer;
		this.scene = pScene;
		this.isEnabled = true;
		//this.animate(pFrameDurationEach, pAnimationListener)
		// change texture! 
		long durations[] = new long[1];
		durations[0] = 100000;
		int[] pFrames = new int[1];
		pFrames[0]= 1;
		this.animate(durations, pFrames, 0);
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}

    protected void onManagedUpdate(float pSecondsElapsed) {
    	super.onManagedUpdate(pSecondsElapsed);
    		if (this.isEnabled) {
    			if (this.player.collidesWith(this)) {
    				this.scene.levelComplete();
    				
    			}
	
    	}
    }
}

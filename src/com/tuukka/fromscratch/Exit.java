package com.tuukka.fromscratch;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


public class Exit extends Sprite {
	private boolean isEnabled;

	private GameScene scene;
	private Player player;

	public Exit(int x, int y, TiledTextureRegion exit_region,
			VertexBufferObjectManager vbom) {
		super(x, y, exit_region, vbom);
	}

	public void enable(Player pPlayer, GameScene pScene) {
		this.player = pPlayer;
		this.scene = pScene;
		// change texture! 
		
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

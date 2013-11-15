package com.tuukka.fromscratch;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


public class Pill extends Sprite {

	public Pill(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}
	public void relocate(float X, float Y) 
	{
		float pX = (float)Math.random() * X;
		float pY = (float)Math.random() * Y;
		/*this.setX(pX);
		this.setY(pY);*/
		this.setPosition(pX, pY);
	}
	public void getEaten(Player player) {
		this.setVisible(false);
        this.setIgnoreUpdate(true); 
        this.dispose();
	}
}

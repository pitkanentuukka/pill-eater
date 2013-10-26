package com.tuukka.fromscratch;

import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import org.andengine.engine.camera.Camera;
import com.tuukka.fromscratch.SceneManager.SceneType;

public class MenuScene extends BaseScene implements IOnMenuItemClickListener {

	private final int MENU_PLAY = 0;
	private final int MENU_OPTIONS = 1;
	@Override
	public void createScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneManager.SceneType.SCENE_MENU;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMenuItemClicked(
			org.andengine.entity.scene.menu.MenuScene pMenuScene,
			IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
		switch(pMenuItem.getID())
		{
			case MENU_PLAY:
				sceneManager.createGameScene();
				return true;
			case MENU_OPTIONS:
				// show options!
				// how?
				return true;
			default:
				return false;
		}
	}

	private void createBackground()
	{
	    attachChild(new Sprite(400, 240, resourcesManager.menu_background_region, vbom)
	    {
	        @Override
	        protected void preDraw(GLState pGLState, Camera pCamera) 
	        {
	            super.preDraw(pGLState, pCamera);
	            pGLState.enableDither();
	        }
	    });
	}
	

}

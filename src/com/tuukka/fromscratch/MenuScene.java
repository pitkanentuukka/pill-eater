package com.tuukka.fromscratch;

import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;

import com.tuukka.fromscratch.SceneManager.SceneType;

public class MenuScene extends BaseScene implements IOnMenuItemClickListener {

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
		// TODO Auto-generated method stub
		return false;
	}

}

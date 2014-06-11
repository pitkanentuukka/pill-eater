package com.tuukka.fromscratch;

import java.util.ArrayList;

public class Level {
	private Player player;
	private Exit exit;

	private ArrayList<Pill> pillList;
	private int pillcount = 0;
	private SceneManager sceneManager;
	
	public GameScene currentScene;
	
	public Level() {
		sceneManager = SceneManager.getInstance();
		currentScene = (GameScene) sceneManager.getCurrentScene();
		
		
	}
	
    public void setPlayerAndExit(Player player, Exit exit) {
    	this.player = player;
    	this.exit = exit;
    }
	
	public void addPill(Pill pill) {
		pillcount++;
		pillList.add(pill);
		
	}

	public void setPlayer(Player player2) {
		// TODO Auto-generated method stub
		this.player = player2;
	}

	public void setExit(Exit exit2) {
		// TODO Auto-generated method stub
		this.exit = exit2;
	}
	
	public void onLoadComplete() {
			
		for (int i=0;i<pillcount;i++) {
			Pill temp = (Pill) pillList.get(i);
			temp.setPlayerAndExit(player, exit);
		}
	}
	
	
	public void eatPill() {
		pillcount--;
    	currentScene.setScore(currentScene.getScore() + 1);
		currentScene.scoreText.setText("score: " + currentScene.getScore());
		if (pillcount < 1) {
			exit.enable(player, currentScene);

		}
	}

}

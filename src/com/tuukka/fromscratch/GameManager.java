package com.tuukka.fromscratch;

public class GameManager {
	
	private float defaultPlayerDensity = 1;
	private float defaultPlayerElasticity = 0.5f;
	private float defaultPlayerFriction = 1;

	private float userSetPlayerDensity;
	private float userSetPlayerElasticity;
	private float userSetPlayerFriction;

	private boolean debugEnabled;
	
	
	//singleton
	private static GameManager INSTANCE = new GameManager();
	
	private GameManager() {
		
	}

	public static GameManager getInstance() 
	{
		return INSTANCE;
	}
	
	public float getPlayerDensity() {
		if (debugEnabled) {
			return userSetPlayerDensity;
		} else {
			return defaultPlayerDensity;
		}
	}

	public float getPlayerElasticity() {
		if (debugEnabled) {
			return userSetPlayerElasticity;
		} else {
			return defaultPlayerElasticity;
		}
	}

	public float getPlayerFriction() {
		if (debugEnabled) {
			return userSetPlayerFriction;
		} else {
			return defaultPlayerFriction;
		}
	}
	
	public boolean getDebug() {
		return this.debugEnabled;
	}
	
	public void setDebugSettings(boolean pDebug, float pPlayerDensity, float pPlayerElasticity, float pPlayerFriction) {
		this.debugEnabled = pDebug;
		this.userSetPlayerDensity = pPlayerDensity;
		this.userSetPlayerElasticity = pPlayerElasticity;
		this.userSetPlayerFriction = pPlayerFriction;
	}
}

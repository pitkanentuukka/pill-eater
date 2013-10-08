package com.tuukka.fromscratch;

public class GameManager {
	
	//singleton
	private static GameManager INSTANCE;
	
	public GameManager() {
		
	}

	public static GameManager getInstance() 
	{
		if (INSTANCE == null) {
			INSTANCE = new GameManager();
		}
		return INSTANCE;
	}
}

package com.tuukka.fromscratch;

import java.util.ArrayList;

public class Level {
	private Player player;
	private Exit exit;

	private ArrayList pillList;
	private int pillcount = 0;
	
	
    public void setPlayerAndExit(Player player, Exit exit) {
    	this.player = player;
    	this.exit = exit;
    }
	
	public void addPill(Pill pill) {
		pillcount++;
		pillList.add(pill);
		
	}

}

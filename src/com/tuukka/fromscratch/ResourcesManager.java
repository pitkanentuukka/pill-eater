package com.tuukka.fromscratch;

public class ResourcesManager {
	
	private static final ResourcesManager INSTANCE = new ResourcesManager();

	private ResourcesManager() {
		// private constructor
	}
	
	public static ResourcesManager getInstance() {
		return INSTANCE;
		
	}
}

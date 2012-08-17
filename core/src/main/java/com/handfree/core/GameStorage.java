package com.handfree.core;

import static playn.core.PlayN.storage;
import playn.core.Json;
import playn.core.PlayN;

public class GameStorage {
    private static GameStorage instance;
    private final Json.Object jsonOb;
    private final String storageKey = "gameStorage";

    private GameStorage() {
	jsonOb = PlayN.json().createObject();
	String jsonS = storage().getItem(storageKey);
    }

    public static GameStorage getInstance() {
	if (instance == null) {
	    instance = new GameStorage();
	}
	return instance;
    }

    public int getInt(String key) {
	return 0;
    }

    public String getString(String key) {
	return null;
    }
}

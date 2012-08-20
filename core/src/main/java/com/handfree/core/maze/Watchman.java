package com.handfree.core.maze;

public class Watchman {
    public interface WatchmanHandler {
	void updateWatchmanToFinish(long miliseconds);

	void finishWatchman();
    }

    private final WatchmanHandler client;
    private final long waitMiliseconds;
    private long currentMiliseconds;
    private final long systemMiliseconds;
    private boolean finished;

    public Watchman(WatchmanHandler client, long waitMiliseconds) {
	super();
	this.client = client;
	this.waitMiliseconds = waitMiliseconds;
	currentMiliseconds = 0;
	systemMiliseconds = System.currentTimeMillis();
	finished = false;
    }

    public void update() {
	if (finished)
	    return;
	currentMiliseconds = System.currentTimeMillis() - systemMiliseconds;
	client.updateWatchmanToFinish(waitMiliseconds - currentMiliseconds);
	if (currentMiliseconds >= waitMiliseconds) {
	    finished = true;
	    client.finishWatchman();
	}
    }
}

package models;

import models.alien.Alien;
import utils.Constants;

import java.util.Random;

public class RunModeState extends State {
    private int width;
    private int height;
    private Alien[] aliens;
    private boolean isPaused;
    private Room[] rooms;
    private int currentRoom;
    private PowerUp[] powerUps;
    private Player player;
    private Door door;
    private Key key;
    private int showKeyFor;
    private int timeoutAfter;
    private boolean completed;

    public RunModeState(Alien[] aliens, boolean isPaused, Room[] rooms, PowerUp[] powerUps, Player player, Door door) {
        this.width  = 0;
        this.height = 0;
        this.aliens = aliens;
        this.isPaused = isPaused;
        this.rooms = rooms;
        this.currentRoom = 0;
        this.powerUps = powerUps;
        this.player = player;
        this.door = door;
        setKey();
        this.showKeyFor = 0;
        resetTimeoutAfter();
        this.completed = false;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        // TODO: Remove magic number
        this.door.setXPosition(width - 50);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        // TODO: Remove magic number
        this.door.setYPosition(height - 50);
    }

    public Alien[] getAliens() {
        return aliens;
    }

    public void setAliens(Alien[] aliens) {
        this.aliens = aliens;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPause(boolean isPaused) {
        this.isPaused = isPaused;
    }
    
    public Room[] getRooms() {
        return rooms;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void incCurrentRoom() {
        currentRoom++;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public PowerUp[] getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(PowerUp[] powerUps) {
        this.powerUps = powerUps;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public Key getKey() {
        return key;
    }

    public void setKey() {
        var random  = new Random();
        var room    = getRooms()[getCurrentRoom()];
        var objects = room.getObjects();
        var randObj = objects.get(random.nextInt(objects.size()));
        this.key = new Key(randObj);
    }

    public int getShowKeyFor() {
        return showKeyFor;
    }

    public void setShowKeyFor(int showKeyFor) {
        this.showKeyFor = showKeyFor;
    }

    public void decShowKeyFor() {
        showKeyFor = Math.max(showKeyFor - 1, 0);
    }

    public long getTimeoutAfter() {
        return timeoutAfter;
    }

    public void resetTimeoutAfter() {
        // TODO: Remove magic numbers
        timeoutAfter = (int) ((getRooms()[getCurrentRoom()].getObjects().size() * 5 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
    }

    public void decTimeoutAfter() {
        timeoutAfter = Math.max(timeoutAfter - 1, 0);
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted() {
        completed = true;
    }
}
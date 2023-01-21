package models;

import models.alien.Alien;
import models.powerUps.PowerUp;
import utils.Constants;

import java.util.ArrayList;
import java.util.Random;

public class RunModeState extends State {
    // OVERVIEW: Holds the state while in Run Mode (player and alien positions, scores and timeouts, etc...). Mutable.
    private int width;
    private int height;
    private ArrayList<Alien> aliens;
    private boolean isPaused;
    private Room[] rooms;
    private int currentRoom;
    private ArrayList<PowerUp> powerUps;
    private Player player;
    private Door door;
    private Key key;
    private ArrayList<Projectile> projectiles;
    private int showKeyFor;
    private int timeoutAfter;
    private int timeForNextAlien;
    private int timeForNextPowerUp;
    private boolean completed;
    private int hintEffectTimer;
    private int protectionVestEffectTimer;
    private int frames;

    // Constructors

    public RunModeState(ArrayList<Alien> aliens, boolean isPaused, Room[] rooms, ArrayList<PowerUp> powerUps, Player player, Door door, ArrayList<Projectile> projectiles) {
        this.width = 1;
        this.height = 1;
        this.aliens = aliens;
        this.isPaused = isPaused;
        this.rooms = rooms;
        this.currentRoom = 0;
        this.powerUps = powerUps;
        this.player = player;
        this.door = door;
        setKey(new Random());
        this.showKeyFor = 0;
        resetTimeoutAfter();
        resetTimeForNextAlien();
        resetTimeForNextPowerUp();
        this.completed = false;
        this.projectiles = projectiles;
        this.frames = 0;
    }

    // Methods

    public int getWidth() {
        return width;
    }

    // EFFECT: Update the game width. New width should be positive.
    // MODIFIES: Game width.
    public void setWidth(int width) {
        if (width <= 0) throw new IllegalArgumentException();
        this.width = width;
        this.door.setXPosition(width - Constants.ENTITY_DIM);
    }

    public int getHeight() {
        return height;
    }

    // EFFECT: Update the game height. New height should be positive.
    // MODIFIES: Game height.
    public void setHeight(int height) {
        if (height <= 0) throw new IllegalArgumentException();
        this.height = height;
        this.door.setYPosition(height - Constants.ENTITY_DIM);
    }

    public ArrayList<Alien> getAliens() {
        return aliens;
    }

    // EFFECT: Update the Aliens List in the game. The array should not be null.
    // MODIFIES: Aliens
    public void setAliens(ArrayList<Alien> aliens) {
        if (aliens == null) throw new IllegalArgumentException();
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


    // EFFECT: Update the Rooms List in the game. The array should not be null and should not be empty.
    // MODIFIES: Rooms
    public void setRooms(Room[] rooms) {
        // The rooms list cannot be null or empty
        if (rooms == null || rooms.length == 0) throw new IllegalArgumentException();

        // Current room index should be a valid index
        if (currentRoom < 0 || currentRoom >= rooms.length) throw new IllegalArgumentException();
        // Make sure minimum object requirement is met
        for (var room : rooms) {
            if (room.getObjects().size() < room.getMinObjects()) throw new IllegalArgumentException();
        }

        this.rooms = rooms;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    // EFFECT: Update the current room index in the game. Current room index should not be larger or equal than Room list length.
    // MODIFIES: Current room
    public void incCurrentRoom() {
        // Next room index should be a valid index
        if (currentRoom+1 >= rooms.length) throw new IllegalArgumentException();

        currentRoom++;
    }

    public ArrayList<PowerUp> getPowerUps() {
        return powerUps;
    }
    
    // EFFECT: Update the powerUps list in the game. PowerUps list should not be null.
    // MODIFIES: PowerUps
    public void setPowerUps(ArrayList<PowerUp> powerUps) {
        if (powerUps == null) throw new IllegalArgumentException();
        this.powerUps = powerUps;
    }

    public Player getPlayer() {
        return player;
    }

    // EFFECT: Sets/Updates `player` in the game. The player should not be null
    // MODIFIES: player
    public void setPlayer(Player player) {
        // `player` should not be null
        if (player == null) throw new IllegalArgumentException();

        this.player = player;
    }

    public Door getDoor() {
        return door;
    }

    // EFFECT: Sets/Updates `door` in the game. The door should not be null
    // MODIFIES: door
    public void setDoor(Door door) {
        // `door` should not be null
        if (door == null) throw new IllegalArgumentException();

        this.door = door;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Random random) {
        var room = getRooms()[getCurrentRoom()];
        var objects = room.getObjects();
        var randObj = objects.get(random.nextInt(objects.size()));
        this.key = new Key(randObj);
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public int getShowKeyFor() {
        return showKeyFor;
    }

    // EFFECT: Sets/updates showKeyFor in the game. showKeyFor should not be negative
    // MODIFIES: showKeyFor
    public void setShowKeyFor(int showKeyFor) {
        // showKeyFor should be non-negative
        if (showKeyFor < 0) throw new IllegalArgumentException();

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

    public void incTimeoutAfter(int time){ timeoutAfter += ((time * Constants.SECOND_MILLS)/Constants.REPAINT_DELAY_MILLS); }

    public long getTimeForNextAlien() {
        return timeForNextAlien;
    }

    public void resetTimeForNextAlien() {
        // TODO: Remove magic numbers
        timeForNextAlien = (int) ((10 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
    }

    public void decTimeForNextAlien() {
        timeForNextAlien = Math.max(timeForNextAlien - 1, 0);
    }

    public long getTimeForNextPowerUp() {
        return timeForNextPowerUp;
    }

    public void resetTimeForNextPowerUp() {
        // TODO: Remove magic numbers
        timeForNextPowerUp = (int) ((12 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
    }

    public void decTimeForNextPowerUp() {
        timeForNextPowerUp = Math.max(timeForNextPowerUp - 1, 0);
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted() {
        completed = true;
    }
    
        public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }
    public void resetHintEffectTimer(){
        hintEffectTimer = (int) ((10 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
    }
    public void resetProtectionVestEffectTimer(){
        protectionVestEffectTimer = (int) ((20 * Constants.SECOND_MILLS) / Constants.REPAINT_DELAY_MILLS);
    }
    public void decHintEffectTimer() {
        hintEffectTimer = Math.max(hintEffectTimer - 1, 0);
    }
    public void decProtectionVestEffectTimer() {
        protectionVestEffectTimer = Math.max(protectionVestEffectTimer - 1, 0);
    }

    public int getHintEffectTimer() {
        return hintEffectTimer;
    }

    public int getProtectionVestEffectTimer() {
        return protectionVestEffectTimer;
    }

    public void incFrames() {
        frames++;
    }

    public int getFrames() {
        return frames;
    }

    // Invariant Validity Check

    public boolean repOk() {
        // Width and height should be positive
        if (width <= 0 || height <= 0) return false;

        // The aliens list cannot be null
        if (aliens == null) return false;

        // The rooms list cannot be null or empty
        if (rooms == null || rooms.length == 0) return false;

        // Current room index should be a valid index
        if (currentRoom < 0 || currentRoom >= rooms.length) return false;
        // Make sure minimum object requirement is met
        for (var room : rooms) {
            if (room.getObjects().size() < room.getMinObjects()) return false;
        }

        // The powerups list cannot be null
        if (powerUps == null) return false;

        // The player cannot be null
        if (player == null) return false;

        // The door cannot be null
        if (door == null) return false;

        // showKeyFor should be non-negative
        if (showKeyFor < 0) return false;

        // timeoutAfter should be non-negative
        return timeoutAfter >= 0;
    }
}

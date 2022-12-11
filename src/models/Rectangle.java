package models;

import utils.Position;

public class Rectangle {
    private Position position;
    private int width;
    private int height;

    public Rectangle(Position position, int width, int height) {
        this.position = position;
        this.width    = width;
        this.height   = height;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(int xPosition, int yPosition) {
        position = new Position(xPosition, yPosition);
    }

    public void setXPosition(int xPosition) {
        position = new Position(xPosition, position.getY());
    }

    public void setYPosition(int yPosition) {
        position = new Position(position.getX(), yPosition);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean intersects(Rectangle other) {
        int selfX1  = this.position.getX();
        int selfY1  = this.position.getY();
        int selfX2  = this.position.getX() + this.width;
        int selfY2  = this.position.getY() + this.height;
        int otherX1 = other.position.getX();
        int otherY1 = other.position.getY();
        int otherX2 = other.position.getX() + other.width;
        int otherY2 = other.position.getY() + other.height;

        return Math.min(selfX2, otherX2) > Math.max(selfX1, otherX1) && Math.min(selfY2, otherY2) > Math.max(selfY1, otherY1);
    }
}
package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import java.util.Map;
import java.util.HashMap;

public abstract class GameObject implements ICollider{

    //ATTRIBUTES
    private int size;
    private int color;
    private Location location;
    private int outlineColor = ColorUtil.GREEN;
    protected Map<GameObject, Long> collidedList;

    //CONSTRUCTOR
    public GameObject(Location newLoc, int newSize, int newColor) {
        size = newSize;
        color = newColor;
        location = newLoc;
        collidedList = new HashMap<>();
    }

    public void place(Location newLoc) {
        if (location == null) {
            location = newLoc;
        }
    }

    public boolean collidesWith(Location location, int otherObjectSize) {
        double objectsXDistance = Math.abs(this.getLocation().getX() - location.getX());
        double objectsYDistance = Math.abs(this.getLocation().getY() - location.getY());

        return objectsXDistance < (this.getSize() / 2f) + (otherObjectSize / 2f) &&
                objectsYDistance < (this.getSize() / 2f) + (otherObjectSize / 2f);
    }
    @Override
    public boolean collidesWith(GameObject otherObject) {
        boolean result = false;
        int thisCenterX = this.getLocation().getX() + (getSize()/2);
        int thisCenterY = this.getLocation().getY() + (getSize()/2);
        int otherCenterX = (otherObject).getLocation().getX() + (getSize()/2);
        int otherCenterY = (otherObject).getLocation().getY() + (getSize()/2);

        int dx = thisCenterX - otherCenterX;
        int dy = thisCenterY - otherCenterY;
        int distBetweenCentersSqr = (dx*dx + dy*dy);

        int thisRadius = getSize()/2;
        int otherRadius = getSize()/2;
        int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);

        if (distBetweenCentersSqr <= radiiSqr) {
            result = true;
        }
        return result;

    }
    @Override
    public void handleCollision(GameObject otherObject) {
        if (collidedList.get(otherObject) != null &&
                System.currentTimeMillis() - collidedList.get(otherObject) <= 5000) {
            return;
        } else {
            collidedList.put(otherObject, System.currentTimeMillis());
            otherObject.setObjectCollied(this);
            setObjectCollied(otherObject);
        }
    }
    //GETTERS
    public int getSize() {
        return size;
    }
    public int getColor() {
        return color;
    }
    public Location getLocation(){
        return new Location(this.location);
    }
    //SETTERS

    public void setColor(int newColor) {
        this.color = newColor;
    }
    public int getOutlineColor(){
        return outlineColor;
    }
    public void setOutlineColor(int color){
        outlineColor = color;
    }
    public abstract void draw(Graphics g);
    public abstract void setObjectCollied(GameObject otherObject);

    public void setLocation(Location newLocation) {
        setLocation(newLocation.getX(), newLocation.getY());
    }

    public void setLocation(int x,int y){
        this.location = new Location(x,y);
    }

}


package org.csc133.a3;

import com.codename1.ui.Graphics;
import java.util.Random;

public abstract class Movable extends GameObject {

    private int heading;
    private final static int maxHeading = 259;
    private final static int maxSpeed = 10;
    private final static int minSpeed = 5;
    private int speed;
    private int deltaX;
    private int deltaY;
    private int angle;
    private final static Random random = new Random();
    private GameWorld gw;

    public Movable(int speed, int heading, int size, Location location, int color) {
        super(location, size, color);
        this.speed = speed;
        this.heading = heading;
    }

    //calculations for movable objects
    public void move(long elapsedTimeMillis) {

        Location minLocation = gw.getMinLocation();
        Location maxLocation = gw.getMaxLocation();

        deltaX = (int)Math.round((elapsedTimeMillis / 20L) * Math.cos(Math.toRadians(heading)) * speed);
        deltaY = (int)Math.round((elapsedTimeMillis / 20L) * Math.sin(Math.toRadians(heading)) * speed);
        Location newLocation = new Location(getLocation().getX() +deltaX, getLocation().getY() + deltaY);

        if (newLocation.getX() < minLocation.getX()) {
            angle = 180 - heading;
            heading = angle;
        }
        if (newLocation.getX() > maxLocation.getX()) {
            angle = heading;
            heading = 180 - angle;
        }
        if (newLocation.getY() < minLocation.getY()) {
            angle = 90 - heading;
            heading = 270 + angle;
        }
        if (newLocation.getY() > maxLocation.getY()) {
            angle = 90 - heading;
            heading = 270 + angle;
        }
        this.setLocation(this.getLocation().getX() + deltaX, this.getLocation().getY() + deltaY);
    }
    //getter and setter
    public int getHeading() {
        return this.heading;
    }
    public void setHeading(int direction) {
        while (direction < 0) {
            direction = direction + 360;
        }
        direction = direction % 360;
        this.heading = direction;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speedy) {
        speed = speedy;
    }
    public static int headingControl() {
        return random.nextInt(maxHeading);
    }
    public static int speedControl(){
        return random.nextInt(maxSpeed - minSpeed) + minSpeed;
    }

    public void stickAngleStrategy(double stkAngle){
        this.heading = (int) Math.round(stkAngle);
    }

    @Override
    public void draw(Graphics g) {
        double pointX = getLocation().getPointX(getSize());
        double pointY = getLocation().getPointY(getSize());

        g.setColor(getOutlineColor());

        g.drawArc((int)pointX, (int)pointY, getSize(), getSize(), 0, 360);
        double terminalPointX = getLocation().getX() + ((getSize() / 2f) * Math.cos(Math.toRadians(getHeading())));
        double terminalPointY = getLocation().getY() + ((getSize() / 2f) * Math.sin(Math.toRadians(getHeading())));

        g.drawLine(getLocation().getX(),
                getLocation().getY(),
                (int)terminalPointX,
                (int)terminalPointY);
    }
}

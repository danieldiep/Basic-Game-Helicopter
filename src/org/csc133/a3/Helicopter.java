package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public abstract class Helicopter extends Movable implements ISteerable{
    private double stickAngle;
    private int maximumSpeed;
    private int minimumSpeed = 0;
    protected double fuelLevel = 1000;
    private final int maximumFuel = 100;
    private int fuelConsumptionRate = 10;
    private int damageLevel;
    protected int lives;
    protected int lastSkyScraper;
    private int maximumDamage = 100;
    private final long damageTime = 3000;
    private boolean indicateDamage;
    private long damageStartTime;
    private GameObjectCollection c;
    private PlayerHelicopter playerHeli;
    private GameWorld gw;

    public Helicopter(Location location, int heliSize, int color, int speed, int heading) {
        super(speed, heading, heliSize, location, color);
        this.stickAngle = 0;
        maximumSpeed = 50;
        indicateDamage = false;
    }
    //getters and setters
    public double getStickAngle() {
        return stickAngle;
    }
    public void setStickAngle(double stkAngle) {
        if (stkAngle >= 40) {
            this.stickAngle = 40;
        }
        if (stkAngle <= -40) {
            this.stickAngle = -40;
        }
        else {
            this.stickAngle = stkAngle;
        }

    }
    public int getMaximumSpeed() {
        return maximumSpeed;
    }
    public void setMaximumSpeed(int maxSpd) {
        maximumSpeed = maxSpd;
    }
    public int getMaximumDamage() {
        return maximumDamage;
    }
    public int getDamageLevel() {
        return this.damageLevel;
    }
    public void setDamageLevel(){
        damageLevel = 0;
        setMaximumSpeed(0);
    }
    public boolean maxDamageLevel() {
        if (damageLevel == maximumDamage) {
            return true;
        }
        else
            return false;
    }
    public int getLastSkyScraperReached() {
        return this.lastSkyScraper;
    }
    public void setSpeed(int speedy) {

        if (speedy <= minimumSpeed) {
            speedy = 0;
        }
        super.setSpeed(speedy);
    }
    public void setFuelLevel(double fuelLevel){
        this.fuelLevel = fuelLevel;
    }
    public double getFuelLevel(){
        return this.fuelLevel;
    }

    //Left and Right Direction for SteeringAngle
    @Override
    public void turnDirection() {
        if (stickAngle < - 5) {
            this.setHeading(this.getHeading() - 5);
            stickAngle = stickAngle + 5;
        }
        else if (stickAngle > 5) {
            this.setHeading(this.getHeading() + 5);
            stickAngle = stickAngle - 5;
        }
        else {
            this.setHeading(this.getHeading() + (int)stickAngle);
            stickAngle = stickAngle - stickAngle;
        }
    }
    public void heliDamaged(int d){
        damageLevel = damageLevel + d;
        indicateDamage = true;
        damageStartTime = System.currentTimeMillis();
        setMaximumSpeed((100 - damageLevel)/10);
        setSpeed(getSpeed());

    }
    @Override
    public String toString() {
        String x = "Helicopter:" 	+ " loc=" + this.getLocation().getX() + "," + this.getLocation().getY()
                + " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]"
                + " heading=" + this.getHeading()
                + " speed=" + this.getSpeed()
                + " size=" + this.getSize()
                + " maxSpeed=" + maximumSpeed
                + " steeringDirection=" + stickAngle
                + " fuelLevel=" + fuelLevel
                + " damageLevel=" + damageLevel;
        return x ;
    }
    private void drawHeliDamage(Graphics g) {
        double damagePointX = getLocation().getPointX(30);
        double damagePointY = getLocation().getPointY(30);
        g.setColor(ColorUtil.MAGENTA);
        g.drawArc((int)damagePointX, (int)damagePointY, 30, 30, 0, 360);
        Point PointX = new Point((int)(20 * Math.cos(Math.toRadians(100))), (int)(20 * Math.sin(Math.toRadians(225))));
        Point pointY = new Point((int)(20 * Math.cos(Math.toRadians(100))), (int)(20 * Math.sin(Math.toRadians(225))));

        PointX.setX(PointX.getX() + getLocation().getX());
        PointX.setY(PointX.getY() + getLocation().getY());
        pointY.setX(pointY.getX() + getLocation().getX());
        pointY.setY(pointY.getY() + getLocation().getY());

        g.drawLine(PointX.getX(), PointX.getY(), pointY.getX(), pointY.getY());
    }

    @Override
    public void draw(Graphics g) {
        double pointX = getLocation().getPointX(getSize());
        double pointY = getLocation().getPointY(getSize());
        g.setColor(getColor());
        g.fillArc((int)pointX, (int)pointY, getSize(), getSize(), 0, 360);
        super.draw(g);
        if (indicateDamage) {
            long indicateDamageElapsedTime = System.currentTimeMillis() - damageStartTime;
            long lengthOfFlash = damageTime / 4;
            if (indicateDamageElapsedTime > damageTime) {
                indicateDamage = false;
            } else if ((indicateDamageElapsedTime % lengthOfFlash) < lengthOfFlash / 4) {
                drawHeliDamage(g);
            }
        }
    }
    @Override
    public void move(long elapseTimeMillis) {
        this.turnDirection();
        super.move(elapseTimeMillis);
    }
    public boolean isMaxDamageLevel() {
        if (damageLevel == maximumDamage)
            return true;
        return false;
    }
    @Override
    public void setObjectCollied(GameObject otherObject){
        if (otherObject instanceof SkyScraper){
            if (((SkyScraper) otherObject).getSequenceNumber() == (lastSkyScraper + 1)) {
                ++lastSkyScraper;
                gw.baseSound.play();
            }
        }
        else if (otherObject instanceof Bird){
            heliDamaged(5);
            gw.birdSound.play();
        }
        else if (otherObject instanceof RefuelingBlimp){
            if (((RefuelingBlimp) otherObject).getFuel() > 0){
                this.setFuelLevel(getFuelLevel() + (((RefuelingBlimp) otherObject).getFuel()));
            }
            gw.blimpSound.play();
        }
        else if (otherObject instanceof NonPlayerHelicopter) {
            heliDamaged(15);
            gw.nphSound.play();
        }
    }
}

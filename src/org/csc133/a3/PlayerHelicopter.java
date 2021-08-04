package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;

public class PlayerHelicopter extends Helicopter {

    private static final int startSize = 70;
    private static final int startColor = ColorUtil.rgb(0,38,77);
    private static final int startSpeed = 0;
    private static final int startHeading = 50;
    private double fuelConsumptionRate;
    private boolean skyScraperReached = false;

    public PlayerHelicopter(Location loc, int numLives){
        super(loc, startSize, startColor, startSpeed,startHeading);
        this.fuelLevel = 10000;
        fuelConsumptionRate = 2;
        this.lives = numLives;
        this.lastSkyScraper = 0;
    }
    public double getFuelLevel() {
        return fuelLevel;
    }
    public void setFuelLevel(double fl) {
        fuelLevel = fl;
    }
    public int getLastSkyScraperReached() {
        return lastSkyScraper;
    }
    public boolean skyScraperReached(SkyScraper skyScraper) {
        if (skyScraper.getSequenceNumber() == (lastSkyScraper + 1 )) {
            ++lastSkyScraper;
            setSkyScraperReached();
        }
        return false;
    }
    public void setSkyScraperReached(){
        skyScraperReached = true;
    }
    @Override
    public void move(long elapsedTimeMillis){
        setFuelLevel(getFuelLevel() - ((elapsedTimeMillis / 10) * fuelConsumptionRate));
        super.move(elapsedTimeMillis);
    }
}

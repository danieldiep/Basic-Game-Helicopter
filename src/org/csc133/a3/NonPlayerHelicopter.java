package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;

public class NonPlayerHelicopter extends Helicopter{

    private static int nphSize = 70;
    private static int nphSpeed = 2;
    private static int nphHeading = 0;
    private static int nphColor = ColorUtil.rgb(204,102,0);
    private GameObjectCollection c;
    private IStrategy strategy = null;
    private int newSkyScraper = 2;

    public NonPlayerHelicopter(Location location, GameObjectCollection c) {
        super(location, nphSize, nphColor, nphSpeed, nphHeading);
        this.c = c;
    }
    @Override
    public void move(long elapsedTimeMillis) {
        strategy.invokeStrategy(this, c);
        super.move(elapsedTimeMillis);
    }

    public int getSkyScraper() {
        return newSkyScraper;
    }

    //set strategy of npc
    public void setStrategy(IStrategy s) {
        this.strategy = s;
    }
    public void update(){
        newSkyScraper++;
    }
    public int getStrategy() {
        if (strategy instanceof AttackStrategy) {
            return 1;
        }
        else if (strategy instanceof SkyScraperStrategy){
            return 2;
        }
        else
            return 0;
    }
}

package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;

public class Bird extends Movable {
    private static int birdSize = 40;
    private static int birdColor = ColorUtil.rgb(255,0,255);

    public Bird(Location location){
        super(speedControl(),headingControl(),birdSize, location, birdColor);
        setOutlineColor(birdColor);
    }
    @Override
    public void setColor(int color){
    }
    @Override
    public void move(long elapsedTimeMillis){
        int option = (int)(Math.random() * 3);
        int amountToChange = 0;

        if (option == 0) {
            amountToChange = 10;
        }
        else if (option == 1) {
            amountToChange = -10;
        }
        this.setHeading(this.getHeading() + amountToChange);
        super.move(elapsedTimeMillis);

    }
    @Override
    public void setObjectCollied(GameObject otherObject) {
        if (otherObject instanceof PlayerHelicopter){
            super.handleCollision(otherObject);
        }
    }

    public String toString() {
        String x = "Bird:" + " loc=" + this.getLocation().getX() + "," + this.getLocation().getY()
                + " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]"
                + " heading=" + this.getHeading()
                + " speed=" + this.getSpeed()
                + " size=" + this.getSize();
        return x;
    }
}

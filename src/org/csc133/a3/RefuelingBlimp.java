package org.csc133.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;

public class RefuelingBlimp extends Fixed {
    private int fuel;
    private final static int blimpSize = 150;
    private GameWorld gw;
    public RefuelingBlimp(Location location){
        super(blimpSize, location, ColorUtil.rgb(25,77, 0));
        this.fuel = this.getSize() + 500;
    }

    public void emptyBlimpColor() {
        fuel = 0;
        this.setColor(ColorUtil.rgb(153,255,153));
    }
    public int getFuel() {
        return fuel;
    }
    @Override
    public void setObjectCollied(GameObject otherObject){
        if (otherObject instanceof Helicopter){
            emptyBlimpColor();
        }
    }
    public String toString() {
        String x = "RefuelingBlimp:" + " loc=" + this.getLocation().getX() + "," + this.getLocation().getY() + " fuelLevel=" + fuel;
        return x;
    }
    @Override
    public void draw(Graphics g) {
        int pointX = (int)getLocation().getPointX(this.getSize());
        int pointY = (int)getLocation().getPointY(this.getSize());

        Font smallBoldMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_SMALL);
        g.setFont(smallBoldMonospaceFont);
        g.setColor(getColor());
        g.fillArc(pointX, pointY, getSize(), (int)(getSize() * .6), 0, 360);
        g.setColor(ColorUtil.BLACK);
        g.drawArc(pointX, pointY, getSize(), (int)(getSize() * .6), 0, 360);
        g.setColor(ColorUtil.WHITE);
        g.drawString(Integer.toString(getFuel()), pointX + 20, pointY + 20);
    }
}

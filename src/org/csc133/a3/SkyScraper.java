package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;

public class SkyScraper extends Fixed {
    private final int sequenceNumber;
    private boolean wayPoint = false;
    private static int numSkyScrapers = 0;
    private static int skyScraperColor = ColorUtil.rgb(102,127,255);
    private static final int notReachedBorder = ColorUtil.BLUE;
    private static final int hasReachedBorder = ColorUtil.BLACK;
    private static final int startSize = 100;


    public SkyScraper(Location location) {
        super(startSize, location, skyScraperColor);
        numSkyScrapers++;
        sequenceNumber = numSkyScrapers;
    }
    public int getSequenceNumber() {
        return sequenceNumber;
    }
    public static void resetSkyScrapers(){
        numSkyScrapers = 0;
    }
    public void setWayPoint(){
        wayPoint = true;
    }

    @Override
    public void setObjectCollied(GameObject otherObject) {
        if (otherObject instanceof PlayerHelicopter) {
            if (((PlayerHelicopter) otherObject).getLastSkyScraperReached() == sequenceNumber)
                setWayPoint();
        }
    }

    @Override
    public void draw(Graphics g) {
        int size = getSize();
        int anchorPointX = (int)getLocation().getPointX(size);
        int anchorPointY = (int)getLocation().getPointY(size);

        g.setColor(this.getColor());
        g.fillRect(anchorPointX, anchorPointY, size, size);
        g.setColor(ColorUtil.BLACK);
        Font smallBoldMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_SMALL);
        g.setFont(smallBoldMonospaceFont);
        g.drawChar(Integer.toString(sequenceNumber).charAt(0), anchorPointX + (size / 2), anchorPointY + (size / 2));
        if (wayPoint) {
            g.setColor(hasReachedBorder);
        } else {
            g.setColor(notReachedBorder);
        }
        g.drawRect(anchorPointX, anchorPointY, size, size);
    }
    public String toString() {
        String x = "SkyScraper:" + " loc=" + this.getLocation().getX() + "," + this.getLocation().getY()
                + " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "]"
                + " size=" + this.getSize()
                + " seqNumber=" + sequenceNumber;
        return x ;
    }
}

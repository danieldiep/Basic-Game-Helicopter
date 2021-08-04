package org.csc133.a3;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;


/*
    Paints the SevenSegmentDisplay
 */
public abstract class SevenSegmentDisplay extends Component {
    private final int width;
    private final int height;
    private String label = null;
    private Point origin;
    private Point newPoint;
    private DisplayImages displayImages;

    public SevenSegmentDisplay() {
        super();
        width = (int)(DigitImages.getDigitImage(0).getWidth() * 0.8);
        height = (int) (DigitImages.getDigitImage(0).getHeight() * 0.8);
    }

    public int getImageSize() {
        return displayImages.getSize();
    }

    public void setDisplay(DisplayImages displayImages) {
        this.displayImages = displayImages;
    }

    public DisplayImages digitInitalize() {
        return displayImages;
    }

    public void newPoint() {
        newPoint = new Point(newPoint.getX() + width, newPoint.getY());
    }

    public Point getNextPoint() {
        return new Point(newPoint.getX(), newPoint.getY());
    }

    @Override
    public void laidOut() {
        origin = new Point(getX() + 5, getY() + 5);
        newPoint = new Point(origin.getX(), origin.getY());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Font largeBoldMonospaceFont = Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
        g.setFont(largeBoldMonospaceFont.derive(18f, 2));
        g.setColor(ColorUtil.BLACK);
        g.drawStringBaseline(label, origin.getX(), origin.getY() + 22);
        for (int i = 0; i < displayImages.getSize(); ++i) {
            int nextX = getNextPoint().getX();
            int nextY = getNextPoint().getY();
            g.setColor(displayImages.getBackgroundColor(i));
            g.fillRect(nextX + 1, nextY + 1 + 25, width, height + 40);
            g.drawImage(displayImages.getImage(i), nextX, nextY + 25, width, height);
            newPoint();
        }
        newPoint = origin;
        calcPreferredSize();
    }

    @Override
    protected Dimension calcPreferredSize() {
        return new Dimension(width * getImageSize(), height + 30);
    }

    protected void setLabel(String label) {
        this.label = label;
    }
}
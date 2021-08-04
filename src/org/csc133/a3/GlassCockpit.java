package org.csc133.a3;


import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.GridBagConstraints;
import com.codename1.ui.layouts.GridBagLayout;

public class GlassCockpit extends Container {
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final GameClockComponent gameClockDisplay;
    private final InitiateDisplay fuelDisplay;
    private final InitiateDisplay damageDisplay;
    private final InitiateDisplay livesDisplay;
    private final InitiateDisplay headingDisplay;
    private final InitiateDisplay lastSkyScraperDisplay;
    private static final int fuelColor = ColorUtil.GREEN;
    private static final int defaultDamageColor = ColorUtil.GREEN;
    private static final int damageMidColor = ColorUtil.YELLOW;
    private static final int damageHighColor = ColorUtil.rgb(255,51,51);
    private static final int livesColor = ColorUtil.CYAN;
    private static final int lastColor = ColorUtil.CYAN;
    private static final int headingColor = ColorUtil.YELLOW;
    private double newFuelLevel;
    private double newDamageLevel;
    private int newNumLives;
    private int newLastSkyScraper;
    private int newHeading;

    public GlassCockpit() {
        super(new GridBagLayout());
        gameClockDisplay = new GameClockComponent();
        fuelDisplay = new InitiateDisplay(10000, 4,"FUEL", fuelColor);
        damageDisplay = new InitiateDisplay(0, 2, "DAMAGE", defaultDamageColor);
        livesDisplay = new InitiateDisplay(3, 1, "LIVES", livesColor);
        lastSkyScraperDisplay = new InitiateDisplay(1, 1, "LAST", lastColor);
        headingDisplay = new InitiateDisplay(0, 3, "HEADING",headingColor);
        gridLayout();
    }


    public void update(GameObjectCollection c) {
        newFuelLevel = -1;
        newDamageLevel = -1;
        newNumLives = -1;
        newLastSkyScraper = -1;
        newHeading = -1;

        for (GameObject object : c) {
            if (object instanceof PlayerHelicopter) {
                newFuelLevel = ((PlayerHelicopter) object).getFuelLevel();
                newDamageLevel = ((PlayerHelicopter) object).getDamageLevel();
                newNumLives = GameWorld.getLivesLeft();
                newLastSkyScraper = ((PlayerHelicopter) object).getLastSkyScraperReached();
                newHeading = ((PlayerHelicopter) object).getHeading();
                break;
            }
        }

        int damageColor;
        if (newDamageLevel >= 50) {
            if (newDamageLevel >= 85) {
                damageColor = damageHighColor;
            } else {
                damageColor = damageMidColor;
            }
        } else {
            damageColor = defaultDamageColor;
        }

        fuelDisplay.update((int)newFuelLevel, fuelColor);
        damageDisplay.update((int)newDamageLevel, damageColor);
        livesDisplay.update(newNumLives, livesColor);
        lastSkyScraperDisplay.update(newLastSkyScraper, lastColor);
        headingDisplay.update(newHeading - 90, headingColor);
        repaint();
    }

    public void gridLayout() {
        constraints.anchor = GridBagConstraints.LAST_LINE_START;
        constraints.weighty = 1;

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 6;
        constraints.gridheight = 1;
        add(constraints, gameClockDisplay);

        constraints.gridx = 6;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        add(constraints, fuelDisplay);

        constraints.gridx = 10;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        add(constraints, damageDisplay);

        constraints.gridx = 12;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(constraints, livesDisplay);

        constraints.gridx = 13;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(constraints, lastSkyScraperDisplay);

        constraints.gridx = 14;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        add(constraints, headingDisplay);

        this.getStyle().setBgTransparency(255);
        this.getStyle().setBgColor(ColorUtil.LTGRAY);
    }
    @Override
    protected Dimension calcPreferredSize() {
        return super.calcPreferredSize();
    }
    public IClockComponent gameClockComponent() {
        return gameClockDisplay;
    }
}

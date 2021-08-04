package org.csc133.a3;

import com.codename1.ui.geom.Point;
import java.util.Random;

//Set locations for GameObjects
public class Location extends Point{
    private static double avoidAngle;
    private final static Random random = new Random();

    public Location() {
        super(0,0);
    }

    public Location(Location l) {
        super(l.getX(), l.getY());
    }

    public Location(int x, int y) {
        super(x, y);
    }
    public static Location locWithinBound(Location minLoc, Location maxLoc, Location objectLoc, int radius) {
        Location locationReturn;
        do {
            avoidAngle = random.nextDouble() * 2 * Math.PI;
            locationReturn = new Location((int)(radius * Math.cos(avoidAngle)), (int)(radius * Math.sin(avoidAngle)));
            locationReturn.add(objectLoc);
        } while (!checkWithinBounds(locationReturn, minLoc, maxLoc));
        return locationReturn;
    }
    public static Location locWithinBound(Location minLocation, Location maxLocation) {
        Location locationReturn = null;
        while (locationReturn == null) {
            try {
                locationReturn = new Location(
                        (random.nextInt(maxLocation.getX() - minLocation.getX()) + minLocation.getX()),
                        (random.nextInt(maxLocation.getY() - minLocation.getY()) + minLocation.getY()));
            } catch (Exception e){
                System.out.println("Location out of bound");
            }
        }
        return locationReturn;
    }
    public Location add(Location loc){
        setX(getX() + loc.getX());
        setY(getY() + loc.getY());
        return this;
    }
    public static boolean checkWithinBounds(Location check, Location minLocation, Location maxLocation) {
        if (check.getX() < minLocation.getX() || check.getX() > maxLocation.getX() || check.getY() < minLocation.getY() || check.getY() > maxLocation.getY()) {
            return false;
        }
        return true;
    }
    @Override
    public boolean equals(Object loc) {
        return super.equals(loc);
    }
    public double getPointX(int objectSize) {
        return getX() - (objectSize / 2);
    }
    public double getPointY(int objectSize) {
        return getY() - (objectSize / 2);
    }

    public static double getAttackPlayer(Location a, Location b) {

        int locX = b.getX() - a.getY();
        int locY = b.getY() - a.getY();

        double attackPlayerSide = Math.toDegrees(Math.atan2(locY, locX));

        if (attackPlayerSide < 0) {
            attackPlayerSide = attackPlayerSide + 360;

        }
        if (attackPlayerSide > 360) {
            attackPlayerSide = attackPlayerSide % 360;
        }
        return attackPlayerSide;
    }
}

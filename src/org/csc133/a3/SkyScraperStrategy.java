package org.csc133.a3;


public class SkyScraperStrategy implements IStrategy{

    @Override
    public void invokeStrategy(NonPlayerHelicopter h, GameObjectCollection c) {
        SkyScraper newSkyScraper = null;

        for (GameObject obj : c) {
            if (obj instanceof SkyScraper && ((SkyScraper) obj).getSequenceNumber() == h.getSkyScraper()){
                newSkyScraper = (SkyScraper) obj;
                break;
            }
        }
        if (newSkyScraper == null) {
            return;
        }
        double attackSkyScraper = Location.getAttackPlayer(h.getLocation(), newSkyScraper.getLocation());

        h.setStickAngle(attackSkyScraper);
    }
}

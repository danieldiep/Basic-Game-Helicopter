package org.csc133.a3;

import com.codename1.ui.Component;
import com.codename1.ui.Graphics;

public class MapView extends Component {
    private GameObjectCollection goCollectionList = null;
    private Location minLoc = null;
    private Location maxLoc = null;

    public Location getMinLocation() {
        return minLoc;
    }
    public Location getMaxLocation(){
        return maxLoc;
    }
    public void update(GameObjectCollection c) {
        goCollectionList = c;
        repaint();
    }
    @Override
    public void laidOut() {
        minLoc = new Location(getX(), getY());
        maxLoc = new Location((getWidth() + getX()) - 50, (getHeight() + getY()) - 50);
    }
    @Override
    public void paint(Graphics g) {
        g.setAntiAliased(true);
        if (goCollectionList != null) {
            for (GameObject obj : goCollectionList) {
                obj.draw(g);
            }
        }
    }

}

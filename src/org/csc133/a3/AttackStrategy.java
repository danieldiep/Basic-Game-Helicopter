package org.csc133.a3;

public class AttackStrategy implements IStrategy {

    @Override
    public void invokeStrategy(NonPlayerHelicopter h, GameObjectCollection o) {

        Location nph = h.getLocation();
        Location playerHelicopter = o.playerHelicopter().getLocation();

        double attackPlayer = Location.getAttackPlayer(nph, playerHelicopter);

        h.stickAngleStrategy(attackPlayer);
    }

}

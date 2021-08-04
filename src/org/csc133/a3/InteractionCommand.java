package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;


public abstract class InteractionCommand extends Command {
    private final GameObjectCollection c;
    private PlayerHelicopter playerHeli;
    private BGSound bgSound;
    private boolean bPause = false;

    public InteractionCommand(String command, GameObjectCollection c) {
        super(command);
        this.c = c;
    }
    public void setAccelerate() {
        playerHeli = c.playerHelicopter();
        playerHeli.setSpeed(playerHeli.getSpeed() + 1);

    }
    //Setting Helicopter breaks
    public void setBreak() {
        playerHeli = c.playerHelicopter();
        playerHeli.setSpeed(playerHeli.getSpeed() - 1);
    }

    public void turnLeft() {
        playerHeli = c.playerHelicopter();
        playerHeli.setStickAngle(playerHeli.getStickAngle() - 10);
    }

    public void turnRight() {
        playerHeli = c.playerHelicopter();
        playerHeli.setStickAngle(playerHeli.getStickAngle() + 10);
    }

    //Collision of SkyScraper
    public void colliedSkyScraper(int wayPoint) {
        playerHeli = c.playerHelicopter();
        SkyScraper skyScraper = null;
        for (GameObject o : c) {
            if (o instanceof SkyScraper && ((SkyScraper) o).getSequenceNumber() == wayPoint) {
                skyScraper = (SkyScraper) o;
            }
        }
        if (playerHeli != null && skyScraper != null) {
            //playerHeli.skyScraperReached(skyScraper);
        }
        else {
            Dialog.show("Error", "Please enter a number in the correct order to collide with SkyScraper", "OK", null);
        }
    }

    //Collision with RefuelingBlimp
    public void colliedRefuelingBlimp() {
        playerHeli = c.playerHelicopter();
        RefuelingBlimp refuelB = null;
        for (GameObject o : c) {
            if (o instanceof RefuelingBlimp && ((RefuelingBlimp) o).getFuel() != 0) {
                refuelB = ((RefuelingBlimp) o);
                break;
            }
        }
        if (refuelB == null) {
            System.out.println("There are no more Blimps to refuel");
            return;
        }
        playerHeli.setFuelLevel(playerHeli.getFuelLevel() + refuelB.getFuel());
        refuelB.emptyBlimpColor();
    }

    //Collided with Bird
    public void collidedBird() {
        playerHeli = c.playerHelicopter();
       // playerHeli.heliDamaged(10);
    }

    //Collided with NPH
    public void collidedNph() {
        playerHeli = c.playerHelicopter();
        NonPlayerHelicopter nph = null;
        for (GameObject o : c) {
            if (o instanceof NonPlayerHelicopter) {
                nph = (NonPlayerHelicopter) o;
                break;
            }
        }
        if (nph == null) {
            System.out.println("There is no more NHP!");
            return;
        }
        playerHeli.heliDamaged(15);
    }
    //Changes Strategies
    public void changeStrategies() {
        for (GameObject o : c) {
            if (o instanceof NonPlayerHelicopter) {
                if (((NonPlayerHelicopter)o).getStrategy() == 1) {
                    ((NonPlayerHelicopter)o).setStrategy(new SkyScraperStrategy());
                    ((NonPlayerHelicopter)o).update();
                } else if (((NonPlayerHelicopter)o).getStrategy() == 2) {
                    ((NonPlayerHelicopter)o).setStrategy(new AttackStrategy());
                }
            }
        }
    }

}

package org.csc133.a3;
import com.codename1.ui.events.ActionEvent;


public class CommandCollideBird extends InteractionCommand {

    public CommandCollideBird(GameObjectCollection g) {
        super("Collided with a Bird", g);
    }

    public void actionPerformed(ActionEvent e) {
        collidedBird();
    }
}

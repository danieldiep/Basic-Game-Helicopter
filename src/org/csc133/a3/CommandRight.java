package org.csc133.a3;

import com.codename1.ui.events.ActionEvent;

public class CommandRight extends InteractionCommand {

    public CommandRight(GameObjectCollection g) {
        super("Turn Right", g);
    }

    public void actionPerformed(ActionEvent e) {
        turnRight();
    }
}

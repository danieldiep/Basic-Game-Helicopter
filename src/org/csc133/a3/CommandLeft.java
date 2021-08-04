package org.csc133.a3;

import com.codename1.ui.events.ActionEvent;

public class CommandLeft extends InteractionCommand{

    public CommandLeft(GameObjectCollection g) {
        super("Turn Left", g);
    }
    public void actionPerformed(ActionEvent e) {
        turnLeft();
    }

}

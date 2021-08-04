package org.csc133.a3;

import com.codename1.ui.events.ActionEvent;


public class CommandBrake extends InteractionCommand{
    public CommandBrake(GameObjectCollection g) {
        super("Braking", g);
    }
    public void actionPerformed(ActionEvent e) {
        setBreak();
    }
}

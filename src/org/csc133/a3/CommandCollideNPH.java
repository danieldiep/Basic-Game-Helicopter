package org.csc133.a3;

import com.codename1.ui.events.ActionEvent;

public class CommandCollideNPH extends InteractionCommand {

    public CommandCollideNPH(GameObjectCollection g) {
        super("Collied with NPH", g);
    }
    public void actionPerformed(ActionEvent e) {
        collidedNph();
    }
}

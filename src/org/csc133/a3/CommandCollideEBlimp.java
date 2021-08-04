package org.csc133.a3;
import com.codename1.ui.events.ActionEvent;

public class CommandCollideEBlimp extends InteractionCommand{

    public CommandCollideEBlimp(GameObjectCollection g) {
        super("Collied with RefuelingBlimp", g);

    }

    public void actionPerformed(ActionEvent e) {
        colliedRefuelingBlimp();
    }
}

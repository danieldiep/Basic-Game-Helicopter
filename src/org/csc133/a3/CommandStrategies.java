package org.csc133.a3;

import com.codename1.ui.events.ActionEvent;

public class CommandStrategies extends InteractionCommand {

    public CommandStrategies(GameObjectCollection g) {
        super("Change Strategies", g);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        changeStrategies();
    }
}

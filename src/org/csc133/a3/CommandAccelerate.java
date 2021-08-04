package org.csc133.a3;

import com.codename1.ui.events.ActionEvent;

public class CommandAccelerate extends InteractionCommand{
    public CommandAccelerate(GameObjectCollection g){
        super("Accelerate", g);
    }
    public void actionPerformed(ActionEvent e) {
        setAccelerate();
    }
}

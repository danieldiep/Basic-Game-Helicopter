package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Label;
import com.codename1.ui.Dialog;

public class CommandExit extends Command {
    private final GameWorld gw;

    public CommandExit(GameWorld gw) {
        super("Exit");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        gw.timeStop();
        Command yes = new Command("Yes");
        Command no = new Command("No");
        Label label = new Label("");
        Command c = Dialog.show("Are you sure you want to exit", label, yes, no);

        if (c == yes) {
            gw.exit();
        }
        else if (c == no) {
            return;
        }
        gw.timeResumed();
    }
}

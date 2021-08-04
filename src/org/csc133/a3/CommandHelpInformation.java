package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;

public class CommandHelpInformation extends Command {
    private final GameWorld gw;

    public CommandHelpInformation(GameWorld gw){
        super("Help");
        this.gw = gw;
    }
    public void actionPerformed(ActionEvent e) {
        gw.timeStop();
        Dialog help = new Dialog("Help");
        Label m = new Label("CONTROL/COMMAND OF SKYMAIL 3000");
        m.setAutoSizeMode(true);
        Label a = new Label("Press \"a\" to Accelerate");
        a.setAutoSizeMode(true);
        Label b = (new Label("          Press \"b\" to Brake"));
        b.setAutoSizeMode(true);
        Label l = (new Label("Press \"l\" to make a Left turn"));
        l.setAutoSizeMode(true);
        Label r =(new Label("Press \"r\" to make a Right turn"));
        r.setAutoSizeMode(true);
        Label k =(new Label("Press \"e\" to collide with Blimp"));
        k.setAutoSizeMode(true);
        Label h =(new Label("Press \"h\" to collide with Bird"));
        h.setAutoSizeMode(true);
        Label t = (new Label("Press \"t\" to tick the clock"));
        t.setAutoSizeMode(true);
        Label x =(new Label("        Press \"x\" to exit"));
        x.setAutoSizeMode(true);
        Label n = (new Label("Press \"n\" to collide with NPH"));
        n.setAutoSizeMode(true);
        help.addAll(m,a,b,l,r,k,h,t,x,n);
        Command ok = new Command("ok");
        Command c = Dialog.show("", help, ok);
        if (c == ok) {
            gw.timeResumed();
            return;
        }

    }
}

package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.table.TableLayout;

public class CommandAboutInformation extends Command {

    public CommandAboutInformation() {
        super("About");
    }

    public void actionPerformed(ActionEvent e) {
        Dialog about = new Dialog("About", new TableLayout(4,1));

        Command ok = new Command("ok");

        about.add(new Label("Name: Daniel Diep"));
        about.add(new Label("Class: CSC133"));
        about.add(new Label("SkyMail 3000 - Ver.101"));

        Command c = Dialog.show("", about, ok);
        if (c == ok) {
            return;
        }
    }
}

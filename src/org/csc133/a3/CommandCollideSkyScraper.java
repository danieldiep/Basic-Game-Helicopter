package org.csc133.a3;


import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;


public class CommandCollideSkyScraper extends InteractionCommand {
    private final GameWorld gw;

    public CommandCollideSkyScraper(GameWorld gw, GameObjectCollection c){
        super("Collided with SkyScraper", c);
        this.gw = gw;

    }
    public void actionPerformed(ActionEvent e) {
        Command enterCommand = new Command("Confirm");
        TextField emptyText = new TextField();
        Command c = Dialog.show("Enter #1-4 to collide with SkyScraper", emptyText, enterCommand);
        try {
            colliedSkyScraper(Integer.parseInt(emptyText.getText()));
        } catch (Exception ex) {
            Dialog.show("Error", "You've inputted a non-integer input", "OK", null);
        }
    }
}

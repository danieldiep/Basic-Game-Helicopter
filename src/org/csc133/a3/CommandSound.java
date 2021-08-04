package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.CheckBox;

public class CommandSound extends Command {
    private final GameWorld gw;
    private static boolean checkSnd = true;
    private boolean bPause = false;
    public CommandSound(GameWorld gw){
        super("Sound Pause/Play");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (((CheckBox)e.getComponent()).isSelected()){
            gw.backgroundSound.pause();
            checkSnd = false;
        }
        else {
            gw.backgroundSound.play();
            checkSnd = true;
        }
    }
    public static boolean checkSound(){
        return checkSnd;
    }
}

package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.GridBagConstraints;
import com.codename1.ui.layouts.GridBagLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class ButtonGrid extends Container implements ActionListener {
    private static Button accelerateButton;
    private static Button leftButton;
    private static Button brakeButton;
    private static Button rightButton;
    private static Button soundButton;
    private boolean bPause = false;
    private GameWorld gw;

    public ButtonGrid() {
        super(new GridBagLayout());
        accelerateButton = new Button("Accelerate");
        brakeButton = new Button("Brake");
        leftButton = new Button("Left");
        rightButton = new Button("Right");
        soundButton = new Button("Pause/Play");
        soundButton.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 0;

        constraints.gridx = 0;
        add(constraints, accelerateButton);
        styleButton(accelerateButton);
        constraints.gridx = 1;
        add(constraints, brakeButton);
        styleButton(brakeButton);
        constraints.gridx = 2;
        add(constraints, leftButton);
        styleButton(leftButton);
        constraints.gridx = 3;
        add(constraints, rightButton);
        styleButton(rightButton);
        constraints.gridx = 4;
        add(constraints, soundButton);
        styleButton(soundButton);
    }

    public void styleButton(Button b) {
        b.getUnselectedStyle().setBgTransparency(255);
        b.getUnselectedStyle().setBgColor(ColorUtil.rgb(0,204,204));
        b.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
        b.getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
        b.getUnselectedStyle().setPaddingTop(1);
        b.getUnselectedStyle().setPaddingBottom(1);
        b.getUnselectedStyle().setPaddingLeft(1);
        b.getUnselectedStyle().setPaddingRight(1);
    }
    public static void addKeyListeners(CommandAccelerate accelerate, CommandBrake brake, CommandLeft left, CommandRight right) {
        accelerateButton.addActionListener(accelerate);
        brakeButton.addActionListener(brake);
        leftButton.addActionListener(left);
        rightButton.addActionListener(right);
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        bPause = !bPause;
        if (CommandSound.checkSound() == true) {
            if (bPause) {
                gw.backgroundSound.pause();
                System.out.println("The sound is being paused");
            } else {
                gw.backgroundSound.play();
                System.out.println("The sound is being played");
            }
        }
    }
}

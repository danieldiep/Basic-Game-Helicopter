package org.csc133.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.CheckBox;

public class  Game extends Form implements Runnable{
    private final GameWorld gw;
    private final UITimer timer;
    private final long timerTimeMillis = 20;
    private final CommandAccelerate accelerateCommand;
    private final CommandBrake brakeCommand;
    private final CommandLeft leftCommand;
    private final CommandRight rightCommand;
    private final Command nphCommand;
    private final Command skyScraperCommand;
    private final Command refuelingBlimpCommand;
    private final Command birdCommand;
    private final Command strategyCommand;
    private final Command aboutCommand;
    private final Command helpCommand;
    private final Command exitCommand;
    private Command soundCommand;
    public Game() {

        MapView mv = new MapView();
        GlassCockpit hud = new GlassCockpit();
        ButtonGrid button = new ButtonGrid();
        this.setLayout(new BorderLayout());
        Toolbar myToolbar = new Toolbar();

        this.setToolbar(myToolbar);
        myToolbar.setTitle("SkyMail 3000");
        myToolbar.setTitleCentered(true);

        hud.getStyle().setBorder(Border.createLineBorder(2, ColorUtil.CYAN));
        mv.getStyle().setBorder(Border.createLineBorder(2, ColorUtil.MAGENTA));

        this.add(BorderLayout.NORTH, hud);
        this.add(BorderLayout.CENTER, mv);
        this.add(BorderLayout.SOUTH, button);
        show();
        gw = new GameWorld(mv, hud, hud.gameClockComponent());
        gw.init();

        timer = new UITimer(this);
        timer.schedule((int)timerTimeMillis, true, this);

        accelerateCommand = new CommandAccelerate(gw.getGameObjectCollection());
        brakeCommand = new CommandBrake(gw.getGameObjectCollection());
        leftCommand = new CommandLeft( gw.getGameObjectCollection());
        rightCommand = new CommandRight(gw.getGameObjectCollection());
        nphCommand = new CommandCollideNPH( gw.getGameObjectCollection());
        skyScraperCommand = new CommandCollideSkyScraper(gw, gw.getGameObjectCollection());
        refuelingBlimpCommand = new CommandCollideEBlimp( gw.getGameObjectCollection());
        birdCommand = new CommandCollideBird(gw.getGameObjectCollection());
        strategyCommand = new CommandStrategies(gw.getGameObjectCollection());
        aboutCommand = new CommandAboutInformation();
        helpCommand = new CommandHelpInformation(gw);
        exitCommand = new CommandExit(gw);
        soundCommand = new CommandSound(gw);
        CheckBox soundBox = new CheckBox("Sound Pause/Play");
        soundBox.setCommand(soundCommand);

        myToolbar.addCommandToSideMenu(exitCommand);
        myToolbar.addCommandToSideMenu(strategyCommand);
        myToolbar.addCommandToSideMenu(aboutCommand);
        myToolbar.addCommandToSideMenu(helpCommand);
        myToolbar.addComponentToSideMenu(soundBox);


        ButtonGrid.addKeyListeners(accelerateCommand, brakeCommand, leftCommand, rightCommand);
        addKeyListener('a', accelerateCommand);
        addKeyListener('b', brakeCommand);
        addKeyListener('l', leftCommand);
        addKeyListener('r', rightCommand);
        addKeyListener('e', refuelingBlimpCommand);
        addKeyListener('s', skyScraperCommand);
        addKeyListener('n', nphCommand);
        addKeyListener('g', birdCommand);
        addKeyListener('x', exitCommand);

        show();
    }

    @Override
    public void run() {
        gw.tick(timerTimeMillis);
    }

}

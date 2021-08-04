package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class GameWorld {

    private final GameObjectCollection goCollectionList = new GameObjectCollection();
    private static int lives = 3;
    private static Location minLoc = new Location(0,768);
    private static Location maxLoc = new Location(1024,0);
    private final IClockComponent gameClockTime;
    private final int totalSkyScrapers = 4;
    private final int totalBlimps = 4;
    private final int totalBird = 4;
    private final MapView mv;
    private final GlassCockpit hud;
    private Location startLocation;
    private boolean sound;
    protected static BGSound backgroundSound;
    protected static Sound baseSound;
    protected static Sound birdSound;
    protected static Sound blimpSound;
    protected static Sound lifeSound;
    protected static Sound nphSound;
    protected static Sound winSound;
    protected static Sound loseSound;

    public GameWorld(MapView map, GlassCockpit hud, IClockComponent gameClock) {
        this.mv = map;
        this.hud = hud;
        minLoc = map.getMinLocation();
        maxLoc = map.getMaxLocation();
        gameClockTime = gameClock;

        //Background Song Cited: vieille.merde.free.fr/skm/sounds/camion.wav on https://www.findsounds.com/ISAPI/search.dll
        backgroundSound = new BGSound("bgSound.wav");
        backgroundSound.setVolume(60);
    }

    public void init() {
        addSkyScraper();
        addHelicopter();
        addNPH();
        addBird();
        addRefuelingBlimp();
        mapDisplay();
        sound = true;
        mv.update(getGameObjectCollection());
        hud.update(getGameObjectCollection());

        backgroundSound.play();
        gameSounds();

    }
    public static Location getMinLocation() {
        return new Location(minLoc);
    }
    public static Location getMaxLocation(){
        return new Location(maxLoc);
    }

    //reset game to original state after losing a life
    public void reset() {
        goCollectionList.clear();
        SkyScraper.resetSkyScrapers();
        gameClockTime.resetElapsedTime();
        lives = 3;
        init();
    }
    public GameObjectCollection getGameObjectCollection() {
        return goCollectionList;
    }
    //Method to tell player if they won or lost
    public void gameOver() {
        if ( lives == 0) {
            Dialog.show("Game over, YOU LOST!!!","Do you want to play again?", new Command("Yes") {
                public void actionPerformed(ActionEvent evt) {
                    reset();
                }
            }, new Command("No") {
                public void actionPerformed(ActionEvent evt) {
                    exit();
                }
            });
        }

        else {
            Dialog.show("Game over, YOU WON!!!","Do you want to play again?", new Command("Yes") {
                public void actionPerformed(ActionEvent evt) {
                    reset();
                }
            }, new Command("No") {
                public void actionPerformed(ActionEvent evt) {
                    exit();
                }
            });
        }
    }
    //Add PlayerHelicopter to GameWorld
    public void addHelicopter(){
        SkyScraper firstSkyScraper = null;
        for (GameObject potentialFirstSkyscraper : goCollectionList) {
            if (potentialFirstSkyscraper instanceof SkyScraper && ((SkyScraper) potentialFirstSkyscraper).getSequenceNumber() == 1) {
                firstSkyScraper = (SkyScraper) potentialFirstSkyscraper;
            }
        }
        startLocation = firstSkyScraper.getLocation();
        PlayerHelicopter playerHelicopter = new PlayerHelicopter(startLocation, lives);
        goCollectionList.add(playerHelicopter);
    }
    //Add SkyScraper to GameWorld
    public void addSkyScraper() {
        for (int i = 0; i < totalSkyScrapers; i++) {
            SkyScraper skyScraper = new SkyScraper(null);
            Location potentialSkyscraperLocation;
            do {
                potentialSkyscraperLocation = Location.locWithinBound(minLoc, maxLoc);
            } while (checkCollidesWithObjects(potentialSkyscraperLocation, skyScraper.getSize()));
            skyScraper.place(potentialSkyscraperLocation);
            goCollectionList.add(skyScraper);
        }
    }
    //Checks to see if there is a collision with Objects
    public boolean checkCollidesWithObjects(Location location, int size){
        boolean collisionExists = false;
        if (goCollectionList.size() == 0) {
            return false;
        }
        for (GameObject existingObject : goCollectionList) {
            if (existingObject.collidesWith(location, size)) {
                collisionExists = true;
            }
        }
        return collisionExists;
    }
    //Adds Bird to GameWorld
    public void addBird() {
        for (int i = 0; i < totalBird; ++i) {
            Bird birdToAdd = new Bird(null);
            Location potentialBirdLocation;
            do {
                potentialBirdLocation = Location.locWithinBound(minLoc, maxLoc);
            } while (checkCollidesWithObjects(potentialBirdLocation, birdToAdd.getSize()));
            birdToAdd.place(potentialBirdLocation);
            goCollectionList.add(birdToAdd);
        }

    }
    //Add RefuelingBlimps to GameWorld
    public void addRefuelingBlimp(){
        for (int i = 0; i < totalBlimps; ++i) {
            RefuelingBlimp blimpToAdd = new RefuelingBlimp(null);
            Location potentialBlimpLocation;
            do {
                potentialBlimpLocation = Location.locWithinBound(minLoc, maxLoc);
            } while (checkCollidesWithObjects(potentialBlimpLocation, blimpToAdd.getSize()));
            blimpToAdd.place(potentialBlimpLocation);
            goCollectionList.add(blimpToAdd);
        }
    }
    //Add NPH to GameWorld
    public void addNPH(){
        Location playerHeli = getGameObjectCollection().playerHelicopter().getLocation();
        Location nphLoc;
        for (int i = 0; i < 4; i++) {
            NonPlayerHelicopter nph = new NonPlayerHelicopter(null, goCollectionList);
            do {
                nphLoc = Location.locWithinBound(minLoc, maxLoc, playerHeli, 500);
            }
            while (!Location.checkWithinBounds(nphLoc, minLoc, maxLoc)
                    && !checkCollidesWithObjects(nphLoc, nph.getSize()));
            nph.place(nphLoc);
            goCollectionList.add(nph);
            if (i % 2 == 0) {
                nph.setStrategy(new AttackStrategy());
            }
            else
                nph.setStrategy(new SkyScraperStrategy());
        }
    }

    public void mapDisplay() {
        System.out.printf("MapView:\n", + (int)mv.getMinLocation().getX(), + (int)mv.getMinLocation().getY(), + (int)mv.getMaxLocation().getX(), + (int)mv.getMaxLocation().getY());
        for (GameObject object : goCollectionList) {
            System.out.println(object.toString());
        }
    }
    //helicopter fuel reduces, all movable objects updates, game clock increase,
    public void tick(long elapsedTimeMillis) {
        PlayerHelicopter playerHeli = goCollectionList.playerHelicopter();
        Object removeObject= null;

        if (playerHeli.getDamageLevel() >= playerHeli.getMaximumDamage() || playerHeli.getFuelLevel() <= 0) {
            lifeTracker();
        }
        else if (playerHeli.getLastSkyScraperReached() == totalSkyScrapers) {
            gameOver();
        }
        for (GameObject o : goCollectionList) {
            if (o instanceof Movable) {
                ((Movable) o).move(elapsedTimeMillis);
            }
            if (o instanceof NonPlayerHelicopter && ((NonPlayerHelicopter) o).getDamageLevel() > 100) {
                removeObject = o;
            }
        }
        if (removeObject  != null){
            goCollectionList.remove(removeObject);
        }
        hud.update(getGameObjectCollection());
        mv.update(getGameObjectCollection());
        checkCollision();
    }
    public void timeStop() {
         gameClockTime.stopElapsedTime();
    }
    public void timeResumed(){
         gameClockTime.startElapsedTime();
    }
    //Tracks Life and reset when loses life
    public void lifeTracker(){
        lifeSound.play();
        lives--;
        PlayerHelicopter playerHelicopter = goCollectionList.playerHelicopter();
        playerHelicopter.setFuelLevel(10000);
        playerHelicopter.setDamageLevel();
        playerHelicopter.setLocation(startLocation);
        playerHelicopter.setSpeed(0);

        if (lives == 0){
            gameOver();
        }
    }
    public void checkCollision() {
        for (int i = 0; i < goCollectionList.size() - 1;  ++i){
            for (int a = i + 1; a < goCollectionList.size(); ++a){
                if (goCollectionList.get(i).collidesWith(goCollectionList.get(a))){
                    goCollectionList.get(i).handleCollision(goCollectionList.get(a));
                }
            }
        }
    }
    public void gameSounds() {
        //baseSound Cited: http://gaschamber.hestfamily.com/tfc/sound/duckhunt/
        baseSound = new Sound("baseCollision.wav");
        baseSound.setVolume(40);
        //birdSound Cited: http://robmartino.com/sounds/Animal%20WAVs/
        birdSound = new Sound("birdCollision.wav");
        birdSound.setVolume(40);
        //blimpSound Cited: http://www.utc.fr/si28/ProjetsUpload/P2006_si28p004/flash_puzzle/sons/rush/
        blimpSound = new Sound("blimpCollision.wav");
        blimpSound.setVolume(40);
        //lifeSound http://d-gun.com/files/sounds/
        lifeSound = new Sound("explosion.wav");
        lifeSound.setVolume(30);
        //nphSound Cited: http://www.superluigibros.com/super-mario-rpg-sound-effects-wav
        nphSound = new Sound("nphSound.wav");
        nphSound.setVolume(40);
    }
    public static int getLivesLeft() {
        return lives;
    }
    public void exit() {
        System.exit(0);
    }

}


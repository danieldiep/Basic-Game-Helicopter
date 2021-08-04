package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Image;

import java.util.ArrayList;


public class GameClockComponent extends SevenSegmentDisplay implements IClockComponent{
    private final int startColor = ColorUtil.CYAN;
    private final int msStartColor = ColorUtil.BLUE;
    private final int dangerColor = ColorUtil.rgb(179,0,0);
    private final int msDangerColor = ColorUtil.rgb(102,0,0);
    private long totalPausedTime;
    private long updateTime;
    private long startTime;
    private long pausedTime;

    public GameClockComponent() {
        ArrayList<Image> images = new ArrayList<>(6);
        ArrayList<Integer> backgroundColors = new ArrayList<>(6);
        setLabel("GAME TIME");

        for (int i = 0; i < 6; ++i) {
            images.add(DigitImages.getDigitImage(i));
        }
        for (int i = 0; i < 5; ++i) {
            backgroundColors.add(startColor);
        }
        backgroundColors.add(msStartColor);
        setDisplay(new DisplayImages(images, backgroundColors));
        update(0);
        calcPreferredSize();
        startTime = System.currentTimeMillis();
    }
    @Override
    public void resetElapsedTime() {
        pausedTime = 0;
        totalPausedTime = 0;
        updateTime = 0;
        startTime = System.currentTimeMillis();

    }
    @Override
    public void startElapsedTime() {
        if (pausedTime != 0) {
            totalPausedTime += System.currentTimeMillis() - pausedTime;
        }
        else if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }
    }
    @Override
    public void stopElapsedTime() {
        pausedTime = System.currentTimeMillis();
    }
    @Override
    public long getElapsedTime() {
        return updateTime - startTime - totalPausedTime;
    }
    public void update(long elapsedMilliseconds) {
        int minutes = (int)(elapsedMilliseconds / 60000);
        int seconds = (int)((elapsedMilliseconds % 60000) / 1000);
        int tenthsOfSeconds = (int)((elapsedMilliseconds % 1000) / 100);
        digitInitalize().setDigitImage(0, (minutes / 10));
        digitInitalize().setDigitImage(1, (minutes % 10));
        digitInitalize().setColonImage(2);
        digitInitalize().setDigitImage(3, (seconds / 10));
        digitInitalize().setDigitWithDotImage(4, (seconds % 10));
        digitInitalize().setDigitImage(5, (tenthsOfSeconds));
    }
    @Override
    public void laidOut() {
        super.laidOut();
        getComponentForm().registerAnimated(this);
    }
    @Override
    public boolean animate() {
        if (System.currentTimeMillis() - updateTime >= 10) {
            updateTime = System.currentTimeMillis();
            long elapsedTime = updateTime - startTime - totalPausedTime;
            if (elapsedTime >= 600000) {
                for (int i = 0; i < digitInitalize().getSize() - 1; ++i) {
                    digitInitalize().setBackgroundColor(i, dangerColor);
                }
                digitInitalize().setBackgroundColor(digitInitalize().getSize() - 1, msDangerColor);
            }
            update(elapsedTime);
            return true;
        }
        else {
            return false;
        }
    }
}
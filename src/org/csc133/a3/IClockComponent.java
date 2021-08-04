package org.csc133.a3;

/*
    Interface for GameClockComponent with GameWorld
 */
public interface IClockComponent {
    void resetElapsedTime();
    void startElapsedTime();
    void stopElapsedTime();
    long getElapsedTime();
}

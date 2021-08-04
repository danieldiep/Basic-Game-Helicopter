package org.csc133.a3;

import com.codename1.ui.Image;

import java.util.ArrayList;
import java.util.Stack;

/*
    Creates the GlassCockPit display number and labels
 */
public class InitiateDisplay extends SevenSegmentDisplay {
    private final int numDigits;

    public InitiateDisplay(int number, int numDigits, String label, int color) {
        this.numDigits = numDigits;
        setLabel(label);
        update(number, color);
        calcPreferredSize();
    }

    public void update(int number, int color) {
        Stack<Integer> digits = new Stack<>();
        ArrayList<Image> digitImages = new ArrayList<>();
        ArrayList<Integer> backgroundColors = new ArrayList<>();

        while (number > 0) {
            digits.push(number % 10);
            number /= 10;
        }
        while (numDigits > digitImages.size() + digits.size()) {
            digits.add(0);
        }
        for (int i = 0; i < numDigits; ++i) {
            digitImages.add(DigitImages.getDigitImage(digits.pop()));
            backgroundColors.add(color);
        }
        setDisplay(new DisplayImages(digitImages, backgroundColors));
        repaint();
    }
}
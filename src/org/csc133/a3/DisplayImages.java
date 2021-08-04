package org.csc133.a3;

import com.codename1.ui.Image;

import java.util.ArrayList;

/*
    Setter and Getter for the Digit Images, including the background color
 */
public class DisplayImages {
    private final ArrayList<Image> images;
    private final ArrayList<Integer> backgroundColors;

    public DisplayImages(ArrayList<Image> images, ArrayList<Integer> backgroundColors) {
        this.images = images;
        this.backgroundColors = backgroundColors;
    }

    public Image getImage(int index) {
        return images.get(index);
    }

    public void setDigitImage(int index, int digit) {
        images.set(index, DigitImages.getDigitImage(digit));
    }

    public void setDigitWithDotImage(int index, int digit) {
        images.set(index, DigitImages.getDigitWithDotImage(digit));
    }

    public void setColonImage(int index) {
        images.set(index, DigitImages.getColonImage());
    }

    public int getBackgroundColor(int index) {
        return backgroundColors.get(index);
    }

    public void setBackgroundColor(int index, int color) {
        backgroundColors.set(index, color);
    }

    public int getSize() {
        return images.size();
    }
}

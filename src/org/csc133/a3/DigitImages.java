package org.csc133.a3;

import com.codename1.ui.Image;

import java.io.IOException;
import java.util.ArrayList;

/*
   initialize  all of the given digit images
 */
public class DigitImages {
    private static ArrayList<Image> digitImages;
    private static ArrayList<Image> digitWithDotImages;
    private static Image colonImage;
    private static boolean checkImage = false;

    public static void setDigitImages() {
        try {
            digitImages = new ArrayList<>();
            for (int i = 0; i < 10; ++i) {
                digitImages.add(Image.createImage("/LED_digit_" + i + ".png"));
            }

            digitWithDotImages = new ArrayList<>();
            for (int i = 0; i < 10; ++i) {
                digitWithDotImages.add(Image.createImage("/LED_digit_" + i + "_with_dot.png"));
            }

            colonImage = Image.createImage("/LED_colon.png");
            checkImage = true;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static Image getDigitImage(int digit) {
        if (!checkImage) {
            setDigitImages();
        }

        return digitImages.get(digit);
    }

    public static Image getDigitWithDotImage(int digit) {
        if (!checkImage) {
            setDigitImages();
        }

        return digitWithDotImages.get(digit);
    }

    public static Image getColonImage() {
        if (!checkImage) {
            setDigitImages();
        }

        return colonImage;
    }
}

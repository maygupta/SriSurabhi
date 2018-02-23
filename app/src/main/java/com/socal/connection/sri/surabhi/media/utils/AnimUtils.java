package com.socal.connection.sri.surabhi.media.utils;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by vivek on 13/01/18.
 */

public class AnimUtils {
    static int top = 0;
    static int bottom = 1;
    static int left = 2;
    static int right = 3;

    public static void loadAnimate(RelativeLayout relativeLayout) {
        Random random = new Random();
        int current = random.nextInt(3);
        TranslateAnimation translateAnimation = null;
        if (current == top) {
            translateAnimation = new TranslateAnimation(0, 0,
                    -(relativeLayout.getBottom()), 0);
        } else if (current == bottom) {
            translateAnimation = new TranslateAnimation(0, 0
                    , relativeLayout.getBottom(), relativeLayout.getTop());
        } else if (current == left) {
            translateAnimation = new TranslateAnimation(-(relativeLayout.getRight()), 0
                    , 0, 0);
        } else if (current == right) {
            translateAnimation = new TranslateAnimation(relativeLayout.getRight(), relativeLayout.getLeft()
                    , 0, 0);
        }
        translateAnimation.setDuration(500);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        relativeLayout.startAnimation(translateAnimation);
    }
}

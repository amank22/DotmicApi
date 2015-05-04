package com.aman.dotmic.HelperClasses;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by aman on 04-05-2015 in Dotmic.
 * Taken from Android Official tutorials
 * http://developer.android.com/training/animation/screen-slide.html
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            ViewCompat.setAlpha(view,0);

        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                ViewCompat.setTranslationX(view, horzMargin - vertMargin / 2);
            } else {
                ViewCompat.setTranslationX(view, -horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            ViewCompat.setScaleX(view,scaleFactor);
            ViewCompat.setScaleY(view,scaleFactor);;

            // Fade the page relative to its size.
            ViewCompat.setAlpha(view,MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            ViewCompat.setAlpha(view,0);
        }
    }
}

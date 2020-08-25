package com.alim.rushit.UIHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FabScroll extends CoordinatorLayout.Behavior<FloatingActionButton>  {

    private static final String TAG = "ScrollAwareFABBehavior";

    private boolean fabAnimationStarted = false;
    private boolean flingHappened = false;

    public FabScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout
            , @NonNull FloatingActionButton child, @NonNull View directTargetChild
            , @NonNull View target, int axes, int type) {

        return target instanceof RecyclerView;
    }

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout
            , @NonNull final FloatingActionButton child, @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);

        // If animation didn't start, we don't need to care about running the restore animation.
        // i.e.: when the user swipes to another tab in a viewpager. The onNestedPreScroll is never called.
        if (!fabAnimationStarted) {
            return;
        }

        // Animate back when the fling ended (TYPE_NON_TOUCH)
        // or if the user made the touch up (TYPE_TOUCH) but the fling didn't happen.
        if (type == ViewCompat.TYPE_NON_TOUCH || (type == ViewCompat.TYPE_TOUCH && !flingHappened)) {
            ViewCompat.animate(child).translationY(0).start();

            fabAnimationStarted = false;
        }
    }

    @Override
    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout
            , @NonNull FloatingActionButton child, @NonNull View target
            , float velocityX, float velocityY, boolean consumed) {

        // We got a fling. Flag it.
        flingHappened = true;
        return false;

    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout
            , @NonNull FloatingActionButton child, @NonNull View target
            , int dx, int dy, @NonNull int[] consumed, int type) {

        if (!fabAnimationStarted) {
            Log.d(TAG, "onStartNestedScroll: animation is starting");
            fabAnimationStarted = true;
            flingHappened = false;
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();

            ViewCompat.animate(child).translationY(child.getHeight() + lp.bottomMargin).start();

        }
    }
}
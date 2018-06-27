package kingfire.ebrahimkarimi.circularimageviewtransition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import static android.content.ContentValues.TAG;

public class RadiusTransition extends Transition {

    private static final String BOUNDS = "viewBounds";
    private static final String[] PROPS = {BOUNDS};
    private float radius;

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public RadiusTransition() {
        setRadius(0);
    }
    public RadiusTransition(float radius) {
        setRadius(radius);
    }

    public RadiusTransition(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    void capValues(TransitionValues transitionValues){
        View view = transitionValues.view;
        if (transitionValues.view instanceof CircularImageView){
            Log.e(TAG, "capValues: " + ((CircularImageView) view).getRadius());
        }
        Rect bounds = new Rect();
        bounds.left = view.getLeft();
        bounds.right = view.getRight();
        bounds.top = view.getTop();
        bounds.bottom = view.getBottom();
        transitionValues.values.put(BOUNDS, bounds);
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        capValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        capValues(transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }

        if (!(startValues.view instanceof CircularImageView)) {
            Log.w(RadiusTransition.class.getSimpleName(), "transition view should be CricularImageView");
            return null;
        }

        CircularImageView view = (CircularImageView) (startValues.view);

        Rect startRect = (Rect) startValues.values.get(BOUNDS);
        Rect endRect = (Rect) endValues.values.get(BOUNDS);
        ObjectAnimator radiusAnimator;
        if (startRect.width() < endRect.width()) {
            radiusAnimator = ObjectAnimator.ofFloat(view,"radius",this.getRadius(), 0);
        } else {
            radiusAnimator = ObjectAnimator.ofFloat(view,"radius",0,this.getRadius());
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(radiusAnimator);
        return animatorSet;
    }
}

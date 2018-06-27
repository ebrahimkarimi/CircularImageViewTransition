package kingfire.ebrahimkarimi.circularimageviewtransition;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.View;

public class SecondActivity extends AppCompatActivity {
    public static String arg_radius = "radius";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        float radius = getIntent().getFloatExtra(arg_radius,0);
        TransitionSet transitionSet = new TransitionSet();
        Transition moveTr = TransitionInflater.from(getApplicationContext()).inflateTransition(android.R.transition.move);
        Transition radiusTr = new RadiusTransition(radius);
        transitionSet.addTransition(moveTr);
        transitionSet.addTransition(radiusTr);
        transitionSet.setDuration(1500);
        getWindow().setSharedElementEnterTransition(transitionSet);
        getWindow().setSharedElementExitTransition(transitionSet);
    }

}

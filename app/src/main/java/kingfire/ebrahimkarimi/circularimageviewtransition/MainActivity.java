package kingfire.ebrahimkarimi.circularimageviewtransition;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CircularImageView circularImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circularImageView = (CircularImageView) findViewById(R.id.img_small);
        circularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(SecondActivity.arg_radius, circularImageView.getRadius());
                ActivityOptionsCompat options = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            MainActivity.this,
                            circularImageView,
                            ViewCompat.getTransitionName(circularImageView));
                }
                startActivity(intent,options.toBundle());

            }
        });
    }
}

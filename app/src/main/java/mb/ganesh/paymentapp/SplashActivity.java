package mb.ganesh.paymentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class SplashActivity extends AppCompatActivity {

    Animation splashAnim;
    TextView textSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashAnim = AnimationUtils.loadAnimation(this,R.anim.scale_anim);
        textSplash = findViewById(R.id.textSplash);



        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this , PaymentActivity.class));
            }
        },2000);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimation();
            }
        },500);
    }

    private void startAnimation() {
        textSplash.setText(getResources().getText(R.string.app_name));
        textSplash.startAnimation(splashAnim);
    }
}
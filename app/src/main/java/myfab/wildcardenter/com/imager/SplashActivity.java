package myfab.wildcardenter.com.imager;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class SplashActivity extends AppCompatActivity {
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar=findViewById(R.id.prcl);
        Handler hn= new Handler();
        hn.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,SearchActivity.class);
                startActivity(intent);
                finish();
                Toasty.success(SplashActivity.this,"Welcome",Toast.LENGTH_SHORT);
            }
        },2000);
    }
}

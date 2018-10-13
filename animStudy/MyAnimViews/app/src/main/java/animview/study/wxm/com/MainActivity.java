package animview.study.wxm.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import animview.study.wxm.com.myanimviews.AnimLoadingBall;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startAnim(View v) {
        ((AnimLoadingBall) v).startAnim();
    }
}

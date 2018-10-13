package animview.study.wxm.com.myanimviews;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import animview.study.wxm.com.R;

/**
 * Created by wxm on 18-10-13.
 */
public class AnimLoadingBall extends RelativeLayout {
    private int color;
    private float fromAlpha;
    private float toAlpha;
    private int duration;
    private float ballSize;

    private LinearLayout llBall1;
    private LinearLayout llBall2;
    private LinearLayout llBall3;
    private LinearLayout llBall4;

    private AnimatorSet animatorSet;

    private static int DEFAULT_BALL_COLOR = Color.parseColor("#F38612");
    private static float DEFAULT_BALL_SIZE = 15.0f;

    public AnimLoadingBall(Context context) {
        this(context, null);
    }

    public AnimLoadingBall(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("NewApi")
    public AnimLoadingBall(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimLoadingBall);

        color = mTypedArray.getColor(R.styleable.AnimLoadingBall_color, DEFAULT_BALL_COLOR);
        fromAlpha = mTypedArray.getFloat(R.styleable.AnimLoadingBall_fromAlpha, 1.0f);
        toAlpha = mTypedArray.getFloat(R.styleable.AnimLoadingBall_toAlpha, 1.0f);
        duration = mTypedArray.getInt(R.styleable.AnimLoadingBall_duration, 2000);
        ballSize = mTypedArray.getDimension(R.styleable.AnimLoadingBall_ballSize, DEFAULT_BALL_SIZE);

        LayoutInflater.from(context).inflate(R.layout.layout_anim_loading_ball, this, true);
        llBall1 = findViewById(R.id.ll_point_circle_1);
        llBall2 = findViewById(R.id.ll_point_circle_2);
        llBall3 = findViewById(R.id.ll_point_circle_3);
        llBall4 = findViewById(R.id.ll_point_circle_4);

        if (color != DEFAULT_BALL_COLOR) {
            ((ImageView) llBall1.getChildAt(0)).setImageTintList(ColorStateList.valueOf(color));
            ((ImageView) llBall2.getChildAt(0)).setImageTintList(ColorStateList.valueOf(color));
            ((ImageView) llBall3.getChildAt(0)).setImageTintList(ColorStateList.valueOf(color));
            ((ImageView) llBall4.getChildAt(0)).setImageTintList(ColorStateList.valueOf(color));
        }

        if (ballSize != DEFAULT_BALL_SIZE) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) ballSize, (int) ballSize);
            (llBall1.getChildAt(0)).setLayoutParams(params);
            (llBall2.getChildAt(0)).setLayoutParams(params);
            (llBall3.getChildAt(0)).setLayoutParams(params);
            (llBall4.getChildAt(0)).setLayoutParams(params);
        }

        initAlphaAnim(initRotationAnim());
    }

    private AnimatorSet.Builder initRotationAnim() {
        ObjectAnimator ballAnim1 = ObjectAnimator.ofFloat(llBall1, "rotation", 0, 360);
        ballAnim1.setDuration(duration);
        ballAnim1.setRepeatCount(Animation.INFINITE);
        ballAnim1.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator ballAnim2 = ObjectAnimator.ofFloat(llBall2, "rotation", 0, 360);
        ballAnim2.setStartDelay(duration/20);
        ballAnim2.setDuration(duration);
        ballAnim2.setRepeatCount(Animation.INFINITE);
        ballAnim2.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator ballAnim3 = ObjectAnimator.ofFloat(llBall3, "rotation", 0, 360);
        ballAnim3.setStartDelay(duration/20 * 5/2);
        ballAnim3.setDuration(duration);
        ballAnim3.setRepeatCount(Animation.INFINITE);
        ballAnim3.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator ballAnim4 = ObjectAnimator.ofFloat(llBall4, "rotation", 0, 360);
        ballAnim4.setStartDelay(duration/20 * 9/2);
        ballAnim4.setDuration(duration);
        ballAnim4.setRepeatCount(Animation.INFINITE);
        ballAnim4.setInterpolator(new AccelerateDecelerateInterpolator());

        animatorSet = new AnimatorSet();
        return animatorSet.play(ballAnim1).with(ballAnim2).with(ballAnim3).with(ballAnim4);
    }

    private void initAlphaAnim(AnimatorSet.Builder builder) {
        if (fromAlpha != toAlpha) {
            ObjectAnimator alphaAnim1 = ObjectAnimator.ofFloat(llBall1, "alpha", fromAlpha, toAlpha, fromAlpha);
            alphaAnim1.setDuration(duration);
            alphaAnim1.setRepeatCount(Animation.INFINITE);
            alphaAnim1.setInterpolator(new AccelerateDecelerateInterpolator());

            ObjectAnimator alphaAnim2= ObjectAnimator.ofFloat(llBall2, "alpha", fromAlpha, toAlpha, fromAlpha);
            alphaAnim2.setStartDelay(duration/20);
            alphaAnim2.setDuration(duration);
            alphaAnim2.setRepeatCount(Animation.INFINITE);
            alphaAnim2.setInterpolator(new AccelerateDecelerateInterpolator());

            ObjectAnimator alphaAnim3= ObjectAnimator.ofFloat(llBall3, "alpha", fromAlpha, toAlpha, fromAlpha);
            alphaAnim3.setStartDelay(duration/20 * 5/2);
            alphaAnim3.setDuration(duration);
            alphaAnim3.setRepeatCount(Animation.INFINITE);
            alphaAnim3.setInterpolator(new AccelerateDecelerateInterpolator());

            ObjectAnimator alphaAnim4= ObjectAnimator.ofFloat(llBall4, "alpha", fromAlpha, toAlpha, fromAlpha);
            alphaAnim4.setStartDelay(duration/20 * 9/2);
            alphaAnim4.setDuration(duration);
            alphaAnim4.setRepeatCount(Animation.INFINITE);
            alphaAnim4.setInterpolator(new AccelerateDecelerateInterpolator());

            builder.with(alphaAnim1).with(alphaAnim2).with(alphaAnim3).with(alphaAnim4);
        }
    }

    public void startAnim() {
        animatorSet.start();
    }

    public void clearAnim() {
        animatorSet.cancel();
    }
}


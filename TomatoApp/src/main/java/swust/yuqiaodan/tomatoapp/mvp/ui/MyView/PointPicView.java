package swust.yuqiaodan.tomatoapp.mvp.ui.MyView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.ui.MyView.ViewValueBean.LittleCircle;
import swust.yuqiaodan.tomatoapp.mvp.ui.MyView.ViewValueBean.LittleCircleEvaluator;


/**
 *
 */
public class PointPicView extends android.support.v7.widget.AppCompatImageView {
    //画笔
    private Paint mPaint;
    //小球的颜色
    private List<Integer> colorList;
    //一开始默认 第一个小球和圆心的夹角
    private float defaultAngle;
    private boolean isPlayAnim = false;
    //控制小球的一些属性 包括小球的半径，小球到圆心的距离
    private LittleCircle littleCircle;

    //中心透明的圈半径
    private float centerCircleRadius;


    //此View中所有的动画集合
    private AnimatorSet mAnimatorSet;


    public PointPicView(Context context) {
        super(context);
        initView();
    }
    public PointPicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public PointPicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }


    private void initView() {
        //初始化画笔
        mPaint = new Paint();
        //初始化小球的颜色 总共12个 其实不用在这写死，可以随机获取color什么的
        colorList = new ArrayList<>(
                Arrays.asList(
                        getResources().getColor(R.color.green),
                        getResources().getColor(R.color.red),
                        getResources().getColor(R.color.pink),
                        getResources().getColor(R.color.powderblue),
                        getResources().getColor(R.color.palegoldenrod),
                        getResources().getColor(R.color.cyan),
                        getResources().getColor(R.color.lime),
                        getResources().getColor(R.color.palegreen),
                        getResources().getColor(R.color.mediumslateblue),
                        getResources().getColor(R.color.mediumvioletred),
                        getResources().getColor(R.color.sandybrown),
                        getResources().getColor(R.color.violet)
                )
        );
        defaultAngle = 0;
        //初始化小球的属性
        littleCircle = new LittleCircle(20, 150);
        centerCircleRadius = 0;
        mAnimatorSet= new AnimatorSet();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        mPaint.setAntiAlias(true);//设置抗锯齿


        //新建图层bitmapMask 大小就是view的大小
        Bitmap bitmapMask = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //在蒙版图层上创建画布
        Canvas canvasMask = new Canvas(bitmapMask);

        Path path = new Path();
        path.addCircle(width / 2, height / 2, centerCircleRadius, Path.Direction.CCW);
        //剪切画布 会在剪切的范围内绘画
        canvasMask.clipOutPath(path);
        //全为白色
        canvasMask.drawColor(Color.WHITE);
        //在View的画布上画出蒙版图层 挡在图片和效果动画之间
        canvas.drawBitmap(bitmapMask, 0, 0, null);


        //保存坐标系原点在右上角的状态
        canvas.save();
        //将坐标系原点移动到正中央 方便计算
        canvas.translate(width / 2, height / 2);
        //初始角 来画动画的
        canvas.rotate(defaultAngle);
        //通过旋转坐标系 来绘制轨迹在弧上的圆 30度画一个 总共12个
        for (int color : colorList) {
            mPaint.setColor(color);
            canvas.drawCircle(littleCircle.defaultRadius, 0, littleCircle.circleRadius, mPaint);
            canvas.rotate(30);
        }

        //如果没有播放动画 则开始播放
        if (!isPlayAnim) {
            initAnim();
        }


    }

    private void initAnim() {
        isPlayAnim = true;
        //旋转的动画
        ObjectAnimator rotaAnim = ObjectAnimator.ofFloat(this, "defaultAngle", 0, 360);
        rotaAnim.setDuration(1000);
        rotaAnim.setInterpolator(new LinearInterpolator());

        //收缩的动画
        ObjectAnimator shrinkAnim = ObjectAnimator.ofObject(this, "littleCircle", new LittleCircleEvaluator(), littleCircle, new LittleCircle(0, 0));
        shrinkAnim.setDuration(1000);
        shrinkAnim.setInterpolator(new LinearInterpolator());


        //中间一个透明的圆不断扩大，显示图片的动画
        ObjectAnimator showPicAnim = ObjectAnimator.ofFloat(this, "centerCircleRadius", centerCircleRadius, Math.max(getMeasuredWidth(), getMeasuredHeight()));
        showPicAnim.setDuration(2000);
        showPicAnim.setInterpolator(new LinearInterpolator());

        //同时播放旋转和收缩的AnimatorSet
        AnimatorSet shrinkAndRota = new AnimatorSet();
        shrinkAndRota.play(rotaAnim).with(shrinkAnim);


        //控制播放顺序
        mAnimatorSet.play(rotaAnim).before(shrinkAndRota);
        mAnimatorSet.play(showPicAnim).after(shrinkAndRota);
        mAnimatorSet.start();

    }

    public float getDefaultAngle() {
        return defaultAngle;
    }

    public void setDefaultAngle(float defaultAngle) {
        this.defaultAngle = defaultAngle;
        postInvalidate();
    }

    public LittleCircle getLittleCircle() {
        return littleCircle;
    }

    public void setLittleCircle(LittleCircle littleCircle) {
        this.littleCircle = littleCircle;
        postInvalidate();
    }

    public float getCenterCircleRadius() {
        return centerCircleRadius;
    }

    public void setCenterCircleRadius(float centerCircleRadius) {
        this.centerCircleRadius = centerCircleRadius;
        postInvalidate();

    }

    public AnimatorSet getmAnimatorSet() {
        return mAnimatorSet;
    }

    public void setmAnimatorSet(AnimatorSet mAnimatorSet) {
        this.mAnimatorSet = mAnimatorSet;
    }
}

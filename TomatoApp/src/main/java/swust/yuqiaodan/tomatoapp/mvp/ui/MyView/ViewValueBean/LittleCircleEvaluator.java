package swust.yuqiaodan.tomatoapp.mvp.ui.MyView.ViewValueBean;

import android.animation.TypeEvaluator;

/**
 * 估值器
 */
public class LittleCircleEvaluator implements TypeEvaluator<LittleCircle> {
    //fraction是当前动画进度 比如50%
    @Override
    public LittleCircle evaluate(float fraction, LittleCircle startValue, LittleCircle endValue) {
        float circleRadius = startValue.circleRadius + fraction * (endValue.circleRadius - startValue.circleRadius);
        float defaultRadius = startValue.defaultRadius + fraction * (endValue.defaultRadius - startValue.defaultRadius);
        return new LittleCircle(circleRadius, defaultRadius);
    }
}

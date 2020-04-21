package swust.yuqiaodan.tomatoapp.mvp.ui.MyView.ViewValueBean;

import java.io.Serializable;

public class LittleCircle implements Serializable {

    //单个小球的半径
    public float circleRadius;
    //小球轨迹的半径
    public float defaultRadius;


    public LittleCircle(float circleRadius, float defaultRadius) {
        this.circleRadius = circleRadius;
        this.defaultRadius = defaultRadius;
    }
}

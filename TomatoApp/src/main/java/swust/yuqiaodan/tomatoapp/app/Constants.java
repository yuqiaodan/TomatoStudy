package swust.yuqiaodan.tomatoapp.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

    //极速api开放平台的appkey www.jisuapi.com
    public static final String JISU_APP_KEY = "35dc30ebaa5940ce";


    //以下两个频道来源不同
    public static final String REALTIME="实时";
    public static final String JOKE="搞笑";

    public static final ArrayList<String> allChannelList = new ArrayList<String>(Arrays.asList(
            "头条",
            REALTIME,
            JOKE,
            "新闻",
            "国内",
            "国际",
            "政治",
            "财经",
            "体育",
            "娱乐",
            "军事",
            "教育",
            "科技",
            "NBA",
            "股票",
            "星座",
            "女性",
            "健康",
            "育儿"));

    //这里应该保存在本地 目前就这样
    public static final ArrayList<String> channelSelected = new ArrayList<String>(Arrays.asList(
            "头条",
            REALTIME,
            "新闻",
            "国内",
            "国际"
    ));

    public static ArrayList<String> getChannelSelected(){
        return channelSelected;
    }
}

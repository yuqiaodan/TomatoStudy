package swust.yuqiaodan.tomatoapp.app;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import swust.yuqiaodan.tomatoapp.R;

public class Constants {

    //极速api开放平台的appkey www.jisuapi.com
    public static final String JISU_APP_KEY = "35dc30ebaa5940ce";

    public static final String LEFT="left";
    public static final String RIGHT="right";

    public static final String QRResult="QRResult";

    public static final String NEWSCHANNELS="NewsChannels";
    public static final String HTMLCONTENT= "HtmlContent";
    public static final String HTMLTITLE= "HtmlTitle";
    public static final String ASTRO= "Astro";

    //以下两个频道来源不同
    public static final String REALTIME="实时";
    public static final String JOKE="搞笑";

    public static final List<String> allChannelList = new ArrayList<>(Arrays.asList(
            "头条",
            REALTIME,
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

    //目前默认必须选择以下三个频道
    public static final Set<String> channelDefaultSelected = new HashSet<String>(Arrays.asList(
            "头条",
            REALTIME,
            "新闻"
    ));




    //这里应该保存在本地 目前就这样
    public static final ArrayList<String> allSource = new ArrayList<String>(Arrays.asList(
            "新浪科技",
            "中国新闻周刊",
            "澎湃新闻",
            "正义网",
            "观察者网",
            "中国青年报",
            "海外网",
            "央视新闻客户端",
            "文汇报",
            "看新闻News",
            "中国经营报",
            "网易新闻客户端"
    ));


    //按顺序保存所有的星座 星座请求id 就是位置+1 比如白羊座id: 1
    public static final List<String> allStars = new ArrayList<String>(Arrays.asList(
            "白羊座",
            "金牛座",
            "双子座",
            "巨蟹座",
            "狮子座",
            "处女座",
            "天秤座",
            "天蝎座",
            "射手座",
            "摩羯座",
            "水瓶座",
            "双鱼座"
    ));
    //按顺序保存所有的星座日期 于上边星座一一对应
    public static final List<String> allStarsTime = new ArrayList<String>(Arrays.asList(
            "3-21~4-19",
            "4-20~5-20",
            "5-21~6-21",
            "6-22~7-22",
            "7-23~8-22",
            "8-23~9-22",
            "9-23~10-23",
            "10-24~11-22",
            "11-23~12-21",
            "12-22~1-19",
            "1-20~2-18",
            "2-19~3-20"
    ));
    //按顺序保存所有的星座拉丁文 于上边星座一一对应
    public static final List<String> allStarsEnglishName = new ArrayList<String>(Arrays.asList(
            "Aries",
            "Taurus",
            "Gemini",
            "Cancer",
            "Leo",
            "Virgo",
            "Libra",
            "Scorpio",
            "Sagittarius",
            "Capricorn",
            "Aquarius",
            "Pisces"
    ));

    //按顺序保存所有的星座拉丁文 于上边星座一一对应
    public static final List<Integer> allStarsIcon = new ArrayList<Integer>(Arrays.asList(
            R.drawable.baiyang_icon,
            R.drawable.jinniu_icon,
            R.drawable.shuangzi_icon,
            R.drawable.juxie_icon,
            R.drawable.shizi_icon,
            R.drawable.chunv_icon,
            R.drawable.tianping_icon,
            R.drawable.tianxie_icon,
            R.drawable.sheshou_icon,
            R.drawable.mojie_icon,
            R.drawable.shuiping_icon,
            R.drawable.shuangyu_icon

    ));



    public static String getSource(){
        return allSource.get(new Random().nextInt(allSource.size()));

    }
}

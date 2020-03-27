package swust.yuqiaodan.tomatoapp.mvp.model.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.AstroFortuneBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseRedponseData;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseResponse;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuNewsBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuRobotQaBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuSearchNewsBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeEntity;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.OpenApiNewsBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.PicEntity;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.TodayHistoryBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.WeatherEntity;

/**
 * 目前待增加接口
 * 1.极速数据笑话接口
 * 2.极速数据Ai机器人接口
 */

public interface Api {
    //新闻等来源一：OpenApi 网易新闻
    String APP_DOMAIN = "https://api.apiopen.top/";
    String APIOPEN_BASEURL_1 = "https://www.apiopen.top/";
    //天气数据来源
    String WEATHER_API = "https://www.tianqiapi.com/api";
    //极速数据baseurl 新闻来源二
    String JISUDATA_BASEURL = "https://api.jisuapi.com/";

    //网易新闻
    @GET(APP_DOMAIN + "getWangYiNews")
    Observable<BaseResponse<List<OpenApiNewsBean>>> getWangYiNews(@Query("page") String page, @Query("count") String count);

    //笑话
    @GET(APP_DOMAIN + "getJoke")
    Observable<BaseResponse<List<JokeEntity>>> getJoke(@Query("page") String page, @Query("count") String count, @Query("type") String type);

    //获取美图 当page=0时，会随机返回一页数据，page>=1时会返回相应页码的数据。
    @GET(APIOPEN_BASEURL_1 + "meituApi")
    Observable<BaseRedponseData<List<PicEntity>>> getPic(@Query("page") String page);

    //获取天气接口
    //version为请求类型 默认v1（每天免费3w次）,city城市名如：成都 （不是成都市）
    @GET(WEATHER_API)
    Observable<WeatherEntity> getWeather(@Query("version") String version, @Query("city") String city, @Query("appid") String appid, @Query("appsecret") String appsecret);

    //极速数据新闻接口（主要来源 稳定）
    @GET(JISUDATA_BASEURL + "news/get")
    Observable<JiSuNewsBean> getJiSuNews(@Query("channel") String channel, @Query("start") String start, @Query("num") String num, @Query("appkey") String appkey);

    //搜索新闻接口
    @GET(JISUDATA_BASEURL + "news/search")
    Observable<JiSuSearchNewsBean> searchNews(@Query("keyword") String keyword, @Query("appkey") String appkey);

    //小I机器人
    //https://api.jisuapi.com/iqa/query?appkey=yourappkey&question=杭州天气
    @GET(JISUDATA_BASEURL + "iqa/query")
    Observable<JiSuRobotQaBean> chatWithRobot(@Query("appkey") String appkey, @Query("question") String question);

    //历史的今天
    //https://api.jisuapi.com/todayhistory/query?appkey=******&month=1&day=2
    @GET(JISUDATA_BASEURL + "todayhistory/query")
    Observable<TodayHistoryBean> todayInHistory(@Query("appkey") String appkey, @Query("month") String month, @Query("day") String day);


    /**
     * @param astroid 星座id  1~12
     * @param date    时间 例 2019-3-22
     * @param appkey
     * @return
     */
    //星座运势查询
    @GET(JISUDATA_BASEURL + "astro/fortune")
    Observable<AstroFortuneBean> getAstroFortune(@Query("astroid") String astroid, @Query("date") String date, @Query("appkey") String appkey);

}

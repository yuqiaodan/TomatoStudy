package swust.yuqiaodan.tomatoapp.mvp.model.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseRedponseData;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseResponse;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeEntity;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.MusicBean.MusicRankBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.MusicBean.MusicSearchBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.OpenApiNewsBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.PicEntity;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.WeatherEntity;

public interface Api {
    //新闻等来源一：OpenApi
    String APP_DOMAIN = "https://api.apiopen.top/";
    String APIOPEN_BASEURL_1="https://www.apiopen.top/";
    //天气数据来源
    String WEATHER_API="https://www.tianqiapi.com/api";
    //新闻数据来源二：极速数据

    //请求音乐
    @GET(APP_DOMAIN+"musicRankings")
    Observable<BaseResponse<List<MusicRankBean>>> getMusicRank();

    @GET(APP_DOMAIN+"searchMusic")
    Observable<BaseResponse<List<MusicSearchBean>>> getSearchMusic(@Query("name") String name);

    //网易新闻
    @GET(APP_DOMAIN+"getWangYiNews")
    Observable<BaseResponse<List<OpenApiNewsBean>>> getNews(@Query("page") String page, @Query("count") String count);

    //笑话
    @GET(APP_DOMAIN+"getJoke")
    Observable<BaseResponse<List<JokeEntity>>> getJoke(@Query("page") String page, @Query("count") String count, @Query("type") String type);

    //获取美图 当page=0时，会随机返回一页数据，page>=1时会返回相应页码的数据。
    @GET(APIOPEN_BASEURL_1+"meituApi")
    Observable<BaseRedponseData<List<PicEntity>>> getPic(@Query("page") String page);

    //获取天气接口
    //version为请求类型 默认v1（每天免费3w次）,city城市名如：成都 （不是成都市）
    @GET(WEATHER_API)
    Observable<WeatherEntity> getWeather(@Query("version") String version, @Query("city") String city, @Query("appid") String appid, @Query("appsecret") String appsecret);

}

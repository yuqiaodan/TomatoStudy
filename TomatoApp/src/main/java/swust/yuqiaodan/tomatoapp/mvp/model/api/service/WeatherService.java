package swust.yuqiaodan.tomatoapp.mvp.model.api.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.WeatherEntity;

import static swust.yuqiaodan.tomatoapp.mvp.model.api.Api.WEATHER_API;

public interface WeatherService {
    @GET(WEATHER_API)
    Observable<WeatherEntity> getWeather(@Query("version") String version, @Query("city") String city,@Query("appid") String appid,@Query("appsecret") String appsecret);
    //version为请求类型 默认v1（每天免费3w次）,city城市名如：成都 （不是成都市）
}

package swust.yuqiaodan.tomatoapp.MyRxjavaStudy.service;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("simpleWeather/query")
    Observable<WeatherBean> getWeather(@Query("city") String city, @Query("key") String key);
}

package swust.yuqiaodan.tomatoapp.mvp.model.api.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseResponse;

import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeEntity;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.NewsEntity;

public interface NewsService {

    @GET("getWangYiNews")
    Observable<BaseResponse<List<NewsEntity>>> getNews(@Query("page") String page, @Query("count") String count);

    @GET("getJoke")
    Observable<BaseResponse<List<JokeEntity>>> getJoke(@Query("page") String page, @Query("count") String count, @Query("type") String type);
}

package swust.yuqiaodan.tomatoapp.mvp.model.api.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseResponse;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.MusicBean.MusicRankBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.MusicBean.MusicSearchBean;


public interface MusicService {
    @GET("musicRankings")
    Observable<BaseResponse<List<MusicRankBean>>> getMusicRank();

    @GET("searchMusic")
    Observable<BaseResponse<List<MusicSearchBean>>> getSearchMusic(@Query("name") String name);



}

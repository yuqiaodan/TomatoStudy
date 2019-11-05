package swust.yuqiaodan.tomatoapp.mvp.model.api.service;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseRedponseData;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.PicEntity;

import static swust.yuqiaodan.tomatoapp.mvp.model.api.Api.BASEURL2;

public interface PicService {
    @GET(BASEURL2+"meituApi")
    Observable<BaseRedponseData<List<PicEntity>>> getPic(@Query("page") String page);
    //当page=0时，会随机返回一页数据，page>=1时会返回相应页码的数据。
}

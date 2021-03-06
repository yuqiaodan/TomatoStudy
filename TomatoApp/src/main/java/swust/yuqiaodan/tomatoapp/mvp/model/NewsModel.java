package swust.yuqiaodan.tomatoapp.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import swust.yuqiaodan.tomatoapp.app.Constants;
import swust.yuqiaodan.tomatoapp.mvp.contract.NewsContract;
import swust.yuqiaodan.tomatoapp.mvp.model.api.Api;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseResponse;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuNewsBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuSearchNewsBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeEntity;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.OpenApiNewsBean;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/20/2019 16:40
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
public class NewsModel extends BaseModel implements NewsContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public NewsModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponse<List<OpenApiNewsBean>>> getWangYiNews(int page, int count) {
        return mRepositoryManager
                .obtainRetrofitService(Api.class)
                .getWangYiNews(String.valueOf(page), String.valueOf(count));
    }

    @Override
    public Observable<BaseResponse<List<JokeEntity>>> getJoke(int page, int count, String type) {
        return mRepositoryManager
                .obtainRetrofitService(Api.class)
                .getJoke(String.valueOf(page), String.valueOf(count), type);
    }


    @Override
    public Observable<JiSuNewsBean> getJiSuNews(String channel, int page) {
        return mRepositoryManager
                .obtainRetrofitService(Api.class)
                .getJiSuNews(channel, String.valueOf(page), "10", Constants.JISU_APP_KEY);
    }

    @Override
    public Observable<JiSuSearchNewsBean> searchNews(String keyword) {
        return mRepositoryManager
                .obtainRetrofitService(Api.class)
                .searchNews(keyword, Constants.JISU_APP_KEY);
    }

}
package swust.yuqiaodan.tomatoapp.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import swust.yuqiaodan.tomatoapp.mvp.contract.PicContract;
import swust.yuqiaodan.tomatoapp.mvp.model.api.service.PicService;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseRedponseData;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseResponse;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.PicEntity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/26/2019 18:28
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class PicModel extends BaseModel implements PicContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public PicModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseRedponseData<List<PicEntity>>> getPic() {
        return mRepositoryManager
                .obtainRetrofitService(PicService.class).getPic(String.valueOf(0));
    }
}
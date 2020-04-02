package swust.yuqiaodan.tomatoapp.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Query;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.BaseRedponseData;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuBaseResponse;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuPicJokeBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JiSuTextJokeBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeShowBean;
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
public interface PicContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void showData(List<PicEntity.ResultBean> data);
        void showMoreData(List<PicEntity.ResultBean> data);
        void showJoke(List<JokeShowBean> data);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<PicEntity> getPic();
        Observable<JiSuBaseResponse<JiSuTextJokeBean>> getTextJoke();
        Observable<JiSuBaseResponse<JiSuPicJokeBean>> getPicJoke();


    }
}

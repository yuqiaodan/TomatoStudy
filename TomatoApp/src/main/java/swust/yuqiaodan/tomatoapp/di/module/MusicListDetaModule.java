package swust.yuqiaodan.tomatoapp.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import swust.yuqiaodan.tomatoapp.mvp.contract.MusicListDetaContract;
import swust.yuqiaodan.tomatoapp.mvp.model.MusicListDetaModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 09/11/2019 10:36
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class MusicListDetaModule {

    @Binds
    abstract MusicListDetaContract.Model bindMusicListDetaModel(MusicListDetaModel model);
}
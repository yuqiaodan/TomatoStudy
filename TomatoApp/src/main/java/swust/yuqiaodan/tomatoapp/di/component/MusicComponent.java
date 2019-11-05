package swust.yuqiaodan.tomatoapp.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import swust.yuqiaodan.tomatoapp.di.module.MusicModule;
import swust.yuqiaodan.tomatoapp.mvp.contract.MusicContract;

import com.jess.arms.di.scope.ActivityScope;

import swust.yuqiaodan.tomatoapp.mvp.ui.activity.MusicActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 09/02/2019 11:29
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = MusicModule.class, dependencies = AppComponent.class)
public interface MusicComponent {
    void inject(MusicActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MusicComponent.Builder view(MusicContract.View view);

        MusicComponent.Builder appComponent(AppComponent appComponent);

        MusicComponent build();
    }
}
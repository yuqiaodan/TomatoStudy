package swust.yuqiaodan.tomatoapp.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import swust.yuqiaodan.tomatoapp.di.module.MusicListDetaModule;
import swust.yuqiaodan.tomatoapp.mvp.contract.MusicListDetaContract;

import com.jess.arms.di.scope.ActivityScope;

import swust.yuqiaodan.tomatoapp.mvp.ui.activity.MusicListDetaActivity;


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
@ActivityScope
@Component(modules = MusicListDetaModule.class, dependencies = AppComponent.class)
public interface MusicListDetaComponent {
    void inject(MusicListDetaActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MusicListDetaComponent.Builder view(MusicListDetaContract.View view);

        MusicListDetaComponent.Builder appComponent(AppComponent appComponent);

        MusicListDetaComponent build();
    }
}
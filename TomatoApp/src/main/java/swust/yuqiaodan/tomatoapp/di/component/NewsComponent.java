package swust.yuqiaodan.tomatoapp.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import swust.yuqiaodan.tomatoapp.di.module.NewsModule;
import swust.yuqiaodan.tomatoapp.mvp.contract.NewsContract;

import com.jess.arms.di.scope.FragmentScope;

import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.HomeNewsFragment;
import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.JokeFragment.ImgFragment;
import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.JokeFragment.VideoFragment;
import swust.yuqiaodan.tomatoapp.mvp.ui.fragment.NewsFragment;


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
@Component(modules = NewsModule.class, dependencies = AppComponent.class)
public interface NewsComponent {
    void inject(NewsFragment fragment);
    void inject(ImgFragment fragment);
    void inject(VideoFragment fragment);//有多个view共用一个p时 在这里并行注入
    void inject(HomeNewsFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        NewsComponent.Builder view(NewsContract.View view);

        NewsComponent.Builder appComponent(AppComponent appComponent);

        NewsComponent build();
    }
}
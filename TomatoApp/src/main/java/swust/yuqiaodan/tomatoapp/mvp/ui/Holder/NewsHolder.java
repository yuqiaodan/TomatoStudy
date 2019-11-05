package swust.yuqiaodan.tomatoapp.mvp.ui.Holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.NewsEntity;

public class NewsHolder extends BaseHolder<NewsEntity> {
    @BindView(R.id.news_img)
    ImageView news_img;
    @BindView(R.id.news_title)
    TextView news_title;
    //图片的请求和缓存 有MVPArms封装提供
/*    private ImageLoader mImageLoader;
    private AppComponent mAppComponent;*/
    public NewsHolder(View itemView) {
        super(itemView);
/*        mAppComponent = ArmsUtils.obtainAppComponentFromContext(itemView.getContext());
        mImageLoader = mAppComponent.imageLoader();*/
    }

    @Override
    public void setData(@NonNull NewsEntity data, int position) {



        news_title.setText(data.getTitle());

/*        mImageLoader.loadImage(itemView.getContext(),
                ImageConfigImpl
                        .builder()
                        .url(data.getImage())
                        .imageView(news_img)
                        .build());*/
        Glide.with(itemView.getContext()).load(data.getImage()).into(news_img);

    }
}

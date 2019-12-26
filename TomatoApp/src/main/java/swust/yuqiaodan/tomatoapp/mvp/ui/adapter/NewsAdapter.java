package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.NewsBean;

public class NewsAdapter extends DefaultAdapter<NewsBean> {
    public NewsAdapter(List<NewsBean> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<NewsBean> getHolder(@NonNull View v, int viewType) {
        return new NewsHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_news;
    }


    public class NewsHolder extends BaseHolder<NewsBean> {
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
        public void setData(@NonNull NewsBean data, int position) {


            news_title.setText(data.getTitle());

/*        mImageLoader.loadImage(itemView.getContext(),
                ImageConfigImpl
                        .builder()
                        .url(data.getImage())
                        .imageView(news_img)
                        .build());*/
            Glide.with(itemView.getContext()).load(data.getImageUrl()).into(news_img);

        }
    }
}

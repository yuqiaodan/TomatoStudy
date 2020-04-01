package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
        ImageView newsImg;
        @BindView(R.id.news_title)
        TextView newsTitle;
        @BindView(R.id.news_source)
        TextView newsSource;
        @BindView(R.id.news_time)
        TextView newsTime;

        public NewsHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void setData(@NonNull NewsBean data, int position) {

            newsTitle.setText(data.getTitle());
            newsSource.setText(data.getSource());
            newsTime.setText(data.getTime());

            Glide.with(itemView.getContext())//使用最小的context（最小其实是imageView的）
                    .asDrawable()//asDrawable()比默认的bitmap占用内存小
                    .load(data.getImageUrl())//图片url
                    .thumbnail(Glide.with(itemView.getContext())
                            .load(R.drawable.loding))//加载中的占位图
                    .into(newsImg);

            //添加itemView的动画 这里是直接为整个itemView添加一个动画
            //也可以为itemView中的局部View 比如ImageView添加动画
            Animation animation = AnimationUtils.loadAnimation(itemView.getContext(),R.anim.scale_50_to_100);
            itemView.startAnimation(animation);
        }
    }
}

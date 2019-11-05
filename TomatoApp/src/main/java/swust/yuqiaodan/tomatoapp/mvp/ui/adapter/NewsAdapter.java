package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.NewsEntity;
import swust.yuqiaodan.tomatoapp.mvp.ui.Holder.NewsHolder;

public class NewsAdapter extends DefaultAdapter<NewsEntity> {
    public NewsAdapter(List<NewsEntity> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<NewsEntity> getHolder(@NonNull View v, int viewType) {
        return new NewsHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_news;
    }
}

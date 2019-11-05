package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeEntity;
import swust.yuqiaodan.tomatoapp.mvp.ui.Holder.JokeHolder;

public class JokeAdapter extends DefaultAdapter<JokeEntity> {
    public JokeAdapter(List<JokeEntity> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<JokeEntity> getHolder(@NonNull View v, int viewType) {
        return new JokeHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_news;
    }
}

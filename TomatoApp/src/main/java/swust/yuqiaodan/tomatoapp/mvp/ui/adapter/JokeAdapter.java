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
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeEntity;

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

    public class JokeHolder extends BaseHolder<JokeEntity> {
        @BindView(R.id.news_img)
        ImageView joke_img;
        @BindView(R.id.news_title)
        TextView joke_title;

        public JokeHolder(View itemView) {
            super(itemView);
        }


        public ImageView getJoke_img() {
            return joke_img;
        }

        @Override
        public void setData(@NonNull JokeEntity data, int position) {
            joke_title.setText(data.getText());
            if (data.getType().equals("image")) {//如果是图片类型
                Glide.with(itemView.getContext()).load(data.getImages())
                        .thumbnail(Glide.with(itemView.getContext()).load(R.drawable.loding))
                        .into(joke_img);
            } else {
                Glide.with(itemView.getContext())
                        .load(data.getThumbnail())
                        .thumbnail(Glide.with(itemView.getContext()).load(R.drawable.loding))
                        .into(joke_img);
            }


        }

    }
}

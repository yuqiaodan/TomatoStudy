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
import swust.yuqiaodan.tomatoapp.mvp.model.entity.MusicBean.MusicSearchBean;

public class MusicListAdapter extends DefaultAdapter<MusicSearchBean> {
    public MusicListAdapter(List<MusicSearchBean> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<MusicSearchBean> getHolder(@NonNull View v, int viewType) {
        return new MusicListHoder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_music_list;
    }




    class MusicListHoder extends BaseHolder<MusicSearchBean>{
        @BindView(R.id.sing_img)
        ImageView sing_img;

        @BindView(R.id.songname)
        TextView songname;

        @BindView(R.id.singername)
        TextView singername;

        public MusicListHoder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull MusicSearchBean data, int position) {
            songname.setText(data.getTitle());
            singername.setText(data.getAuthor());
            Glide.with(itemView.getContext()).load(data.getPic())
                    .thumbnail(Glide.with(itemView.getContext()).load(R.drawable.loding))
                    .into(sing_img);


        }
    }



}

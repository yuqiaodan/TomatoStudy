package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.PicEntity;

public class PicAdapter extends DefaultAdapter<PicEntity> {
    public PicAdapter(List<PicEntity> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<PicEntity> getHolder(@NonNull View v, int viewType) {//返回holder对象
        return new PicHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {//返回item的布局文件
        return R.layout.item_pic;
    }

    public class PicHolder extends BaseHolder<PicEntity> {

        @BindView(R.id.pic_imageview)
        ImageView imageView;

        public PicHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull PicEntity data, int position) {
            Glide.with(itemView.getContext()).load(data.getUrl())
                    .thumbnail(Glide.with(itemView.getContext()).load(R.drawable.loding))
                    .into(imageView);
        }
    }

}

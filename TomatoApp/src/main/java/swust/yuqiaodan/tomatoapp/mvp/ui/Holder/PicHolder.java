package swust.yuqiaodan.tomatoapp.mvp.ui.Holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jess.arms.base.BaseHolder;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.PicEntity;

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

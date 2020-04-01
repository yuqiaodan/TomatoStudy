package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.PicEntity;
import swust.yuqiaodan.tomatoapp.mvp.ui.activity.WelcomeScreenActivity;

public class PicAdapter extends DefaultAdapter<PicEntity.ResultBean> {



    public PicAdapter(List<PicEntity.ResultBean> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<PicEntity.ResultBean> getHolder(@NonNull View v, int viewType) {//返回holder对象


        return new PicHolder(v);

    }

    @Override
    public int getLayoutId(int viewType) {//返回item的布局文件
        return R.layout.item_pic;
    }


/*    @Override
    public void onBindViewHolder(@NonNull BaseHolder<PicEntity.ResultBean> holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        View.OnAttachStateChangeListener listener = new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.scale_50_to_100);
                holder.itemView.startAnimation(animation);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
            }
        };

        holder.itemView.addOnAttachStateChangeListener(listener);
    }*/

/*    @Override
    public void onViewRecycled(@NonNull BaseHolder<PicEntity.ResultBean> holder) {
        super.onViewRecycled(holder);
        ImageView imageView=holder.itemView.findViewById(R.id.pic_imageview);
        if (imageView!=null){
            Glide.with(holder.itemView.getContext()).clear(imageView);
        }
    }*/

    public class PicHolder extends BaseHolder<PicEntity.ResultBean> {

        @BindView(R.id.pic_imageview)
        ImageView imageView;

        public PicHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull PicEntity.ResultBean data, int position) {


            //asDrawable()比默认的bitmap占用内存小
            Glide.with(itemView.getContext()).asDrawable().load(data.getImg())
                    .thumbnail(Glide.with(itemView.getContext()).load(R.drawable.loding))
                    .into(imageView);




        Animation animation = AnimationUtils.loadAnimation(itemView.getContext(),R.anim.scale_50_to_100);
            itemView.startAnimation(animation);




        }
    }

}

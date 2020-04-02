package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vondear.rxui.view.cardstack.RxCardStackView;
import com.vondear.rxui.view.cardstack.tools.RxAdapterStack;

import java.util.Random;

import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.app.Constants;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.JokeShowBean;

import static swust.yuqiaodan.tomatoapp.app.Constants.CARD_COLOR;


//继承于RxAdapterStack<T> T是每个item显示的数据实体范形
public class JokeCardAdapter extends RxAdapterStack<JokeShowBean> {


    public JokeCardAdapter(Context context) {
        super(context);
    }


    /**绑定每个位置的Card的View
     * @param data   每个View内的数据内容
     * @param position 位置
     * @param holder  每个Card内的数据显示方式 holder （参考recycleview）
     */
    @Override
    public void bindView(JokeShowBean data, int position, RxCardStackView.ViewHolder holder) {
            ColorItemViewHolder h = (ColorItemViewHolder) holder;
            h.onBind(data, position);
    }



    @Override
    protected RxCardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {

        View view = getLayoutInflater().inflate(R.layout.list_card_item, parent, false);
        return new ColorItemViewHolder(view);

    }
    /**
     * 为每个位置的card指定具体的布局文件
     */
    @Override
    public int getItemViewType(int position) {
        return R.layout.list_card_item;
    }


    /**
     * 数据的显示 holder
     */
    static class ColorItemViewHolder extends RxCardStackView.ViewHolder {
        View mLayout;
        LinearLayout mContainerContent;
        TextView mTextTitle;
        TextView jokeContent;
        ImageView jokeImage;

        public ColorItemViewHolder(View view) {
            super(view);
            mLayout = view.findViewById(R.id.linear_list_card_item);
            mContainerContent = view.findViewById(R.id.container_list_content);
            mTextTitle = (TextView) view.findViewById(R.id.text_list_card_title);

            jokeContent=view.findViewById(R.id.tv_text_content);

            jokeImage=view.findViewById(R.id.img_show_pic);

        }

        /**
         * @param b 每张卡片展开时要做什么事情
         */
        @Override
        public void onItemExpand(boolean b) {

            //mContainerContent.setVisibility(b ? View.VISIBLE : View.GONE);

        }


        /**
         * 填入卡片内容和颜色 图片等等
         * @param data
         * @param position
         */
        public void onBind(JokeShowBean data, int position) {

            int colorNumber=new Random().nextInt(CARD_COLOR.size());
            mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), CARD_COLOR.get(colorNumber)), PorterDuff.Mode.SRC_IN);
            mTextTitle.setText(String.valueOf(position+1));
            jokeContent.setText(data.getContent());
            if(data.isPic()){
                Glide.with(getContext())
                        .asDrawable()//asDrawable()比默认的bitmap占用内存小
                        .load(data.getPic())//图片url
                        .thumbnail(Glide.with(itemView.getContext())
                                .load(R.drawable.loding))//加载中的占位图
                        .into(jokeImage);
            }
        }

    }
}

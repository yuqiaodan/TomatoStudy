package swust.yuqiaodan.tomatoapp.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

import butterknife.BindView;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.app.Constants;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.ChatBean;

//机器人聊天显示适配器
public class ChatAdapter extends DefaultAdapter<ChatBean> {

    public ChatAdapter(List<ChatBean> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<ChatBean> getHolder(@NonNull View v, int viewType) {

        ChatHolder chatHolder=new ChatHolder(v);
        chatHolder.setIsRecyclable(false);

        return chatHolder;

    }

    @Override
    public int getLayoutId(int viewType) {

        return R.layout.item_robot_chat;

    }

    public class ChatHolder extends BaseHolder<ChatBean> {

        @BindView(R.id.view_chat_left)
        LinearLayout viewLeft;
        @BindView(R.id.view_chat_right)
        LinearLayout viewRight;

        @BindView(R.id.tv_chatLeft)
        TextView chatLeft;
        @BindView(R.id.tv_chatRight)
        TextView chatRight;

        public ChatHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull ChatBean data, int position) {
            if (Constants.RIGHT.equals(data.getLocation())) {
                //显示右边消息
                viewLeft.setVisibility(View.GONE);
                chatRight.setText(data.getContent());
            } else {
                //显示左边消息
                viewRight.setVisibility(View.GONE);
                chatLeft.setText(data.getContent());

            }
        }
    }

}

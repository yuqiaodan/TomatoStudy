package swust.yuqiaodan.tomatoapp.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import swust.yuqiaodan.tomatoapp.R;
import swust.yuqiaodan.tomatoapp.app.Constants;
import swust.yuqiaodan.tomatoapp.app.EventBusTags;
import swust.yuqiaodan.tomatoapp.di.component.DaggerMainComponent;
import swust.yuqiaodan.tomatoapp.mvp.contract.MainContract;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.ChatBean;
import swust.yuqiaodan.tomatoapp.mvp.model.entity.WeatherEntity;
import swust.yuqiaodan.tomatoapp.mvp.presenter.MainPresenter;
import swust.yuqiaodan.tomatoapp.mvp.ui.adapter.ChatAdapter;


/**
 * 机器人对话的初步想法
 * <p>
 */

public class RobotFragment extends BaseFragment<MainPresenter> implements MainContract.View {
    @BindView(R.id.chat_edit)
    EditText chatEdit;
    @BindView(R.id.chat_sendmsg)
    TextView chatSendMsg;
    @BindView(R.id.chat_list)
    RecyclerView mRecyclerView;

    String editQuestion;

    List<ChatBean> mData;
    ChatAdapter mAdapter;


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_robot, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initRecycleView();

    }


    @OnClick({R.id.chat_sendmsg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_sendmsg:

                editQuestion = chatEdit.getText().toString();

                if (TextUtils.isEmpty(editQuestion)) {
                    showMessage("请输入对话内容");
                } else {
                    mPresenter.chatWithRobot(editQuestion);

                    if(mData.size()>100){
                        //聊天记录超过100条 则清空前50条 防止滑动卡顿
                        mData=mData.subList(50,mData.size());
                    }

                    ChatBean chatBean = new ChatBean();
                    chatBean.setLocation(Constants.RIGHT);
                    chatBean.setContent(editQuestion);
                    mData.add(chatBean);
                    chatEdit.setText("");
                    mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
                    //发出回收键盘的消息
                    EventBus.getDefault().post("", EventBusTags.RECOVERYKEYBOARD);
                }
                break;
        }

    }


    public void initRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        mData = new ArrayList<>();
        //添加第一个默认对话
        ChatBean chatBean = new ChatBean();
        chatBean.setLocation(Constants.LEFT);
        chatBean.setContent("您好，这里的您的专属聊天对象，小i机器人，我知天文晓地理，快来和我聊天呀");
        mData.add(chatBean);

        mAdapter = new ChatAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showWeather(WeatherEntity weatherEntity) {

    }

    @Override
    public void showChatContent(ChatBean chatBean) {
        mData.add(chatBean);

        mAdapter.notifyDataSetChanged();

        mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}

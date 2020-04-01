package swust.yuqiaodan.tomatoapp.mvp.model.entity;

import java.io.Serializable;

/**
 * 极速数据接口请求基类 从笑话接口开始启用
 */
public class JiSuBaseResponse <T> implements Serializable {

    private int status;
    private String msg;
    private T result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

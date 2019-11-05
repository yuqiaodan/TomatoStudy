package swust.yuqiaodan.tomatoapp.mvp.model.entity;

import java.io.Serializable;

public class BaseRedponseData<T> implements Serializable {

    /**
     * code : 200
     * message : 成功!
     *
     */

    private int code;
    private String message;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

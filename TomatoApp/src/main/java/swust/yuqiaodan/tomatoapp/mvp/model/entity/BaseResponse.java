package swust.yuqiaodan.tomatoapp.mvp.model.entity;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {


    /**
     * code : 200
     * message : 成功!
     *
     */

    private int code;
    private String message;

    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
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

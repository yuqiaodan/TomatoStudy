package swust.yuqiaodan.tomatoapp.mvp.model.entity;


/**
 * 对话实体类
 *
 * location对话位置 左/右
 * content对话内容
 *
 */
public class ChatBean {

    private String location;
    private String content;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

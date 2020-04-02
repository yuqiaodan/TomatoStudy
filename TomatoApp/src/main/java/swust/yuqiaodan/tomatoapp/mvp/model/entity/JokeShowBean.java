package swust.yuqiaodan.tomatoapp.mvp.model.entity;

//包装一下 笑话的显示实体类
public class JokeShowBean {

    private String content;
    private String pic;
    private String url;
    private boolean isPic;//是否是图片笑话

    public JokeShowBean(String content, String pic, String url, boolean isPic) {
        this.content = content;
        this.pic = pic;
        this.url = url;
        this.isPic = isPic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPic() {
        return isPic;
    }

    public void setPic(boolean pic) {
        isPic = pic;
    }
}

package swust.yuqiaodan.tomatoapp.mvp.model.entity;


/**
 * 将不同数据来源的新闻包装成以下的统一格式 用于界面的展示
 * 目前的主要来源：
 * 极速数据
 * 简书的OpenApi
 */
public class NewsBean {

    //新闻标题
    private String title;
    //新闻时间
    private String time;
    //新闻的主题内容（HTML格式）
    private String htmlContent;
    //新闻来源
    private String source;
    //手机网页版地址
    private String url;
    //图片的地址
    private String imageUrl;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

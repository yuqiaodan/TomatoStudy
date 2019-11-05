package swust.yuqiaodan.tomatoapp.mvp.model.entity;

public class PicEntity {


    /**
     * createdAt : 2016-11-16T08:03:39.788Z
     * publishedAt : 2016-11-16T11:37:18.947Z
     * type : 美图
     * url : http://ww3.sinaimg.cn/large/610dc034jw1f9tmhxq87lj20u011htae.jpg
     */

    private String createdAt;
    private String publishedAt;
    private String type;
    private String url;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

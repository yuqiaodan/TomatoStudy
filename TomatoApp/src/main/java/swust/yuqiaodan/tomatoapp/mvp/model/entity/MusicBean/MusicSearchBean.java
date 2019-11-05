package swust.yuqiaodan.tomatoapp.mvp.model.entity.MusicBean;

public class MusicSearchBean {


    /**
     * author : 葡萄bibo
     * link : http://music.163.com/#/song?id=1337550384
     * pic : http://p2.music.126.net/KBDz5sZkH5Fz5oJhOe9eig==/109951163774563901.jpg?param=300x300
     * type : netease
     * title : 青花瓷
     * lrc :
     * songid : 1337550384
     * url : http://music.163.com/song/media/outer/url?id=1337550384.mp3
     */

    private String author;
    private String link;
    private String pic;
    private String type;
    private String title;
    private String lrc;
    private int songid;
    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

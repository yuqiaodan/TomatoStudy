package swust.yuqiaodan.tomatoapp.mvp.model.entity;

import java.util.List;

public class JiSuPicJokeBean {


    /**
     * pagesize : 1
     * list : [{"content":"夏天来了，热到变形","pic":"http://api.jisuapi.com/xiaohua/upload/201805/08032102_41700.jpg","addtime":"2018-05-08 03:21:02","url":"http://m.kaixinhui.com/detail-107744.html"}]
     */

    private int pagesize;
    private List<ListBean> list;

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * content : 夏天来了，热到变形
         * pic : http://api.jisuapi.com/xiaohua/upload/201805/08032102_41700.jpg
         * addtime : 2018-05-08 03:21:02
         * url : http://m.kaixinhui.com/detail-107744.html
         */

        private String content;
        private String pic;
        private String addtime;
        private String url;

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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

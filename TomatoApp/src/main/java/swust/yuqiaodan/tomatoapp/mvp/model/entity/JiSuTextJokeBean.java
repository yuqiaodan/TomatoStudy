package swust.yuqiaodan.tomatoapp.mvp.model.entity;

import java.util.List;

/**
 * 极速数据 文字笑话
 */
public class JiSuTextJokeBean {


    /**
     * total : 79673
     * pagenum : 1
     * pagesize : 1
     * list : [{"content":"王自健在节目中调侃，对于老婆打自己这件事没有任何不满，没有任何抱怨。 这反映了一个问题，在中国： 老婆就是用来爱的， 老公就是用来打的。 中国妇女的地位真的提高了，可喜可贺！","addtime":"2020-03-28 03:20:02","url":"http://m.kaixinhui.com/detail-128412.html"}]
     */

    private int total;
    private int pagenum;
    private int pagesize;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

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
         * content : 王自健在节目中调侃，对于老婆打自己这件事没有任何不满，没有任何抱怨。 这反映了一个问题，在中国： 老婆就是用来爱的， 老公就是用来打的。 中国妇女的地位真的提高了，可喜可贺！
         * addtime : 2020-03-28 03:20:02
         * url : http://m.kaixinhui.com/detail-128412.html
         */

        private String content;
        private String addtime;
        private String url;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

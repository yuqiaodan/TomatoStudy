package swust.yuqiaodan.tomatoapp.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

public class JiSuRobotQaBean {


    /**
     * status : 0
     * msg : ok
     * result : {"type":"标准回复","content":"杭州今天1℃~8℃ 晴转多云 0-3级 <5.4m/s\r\n建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","relquestion":{"0":"查询天气[那么?][天气地区名|省名|城市别称][天气地区名?]"}}
     */

    private int status;
    private String msg;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * type : 标准回复
         * content : 杭州今天1℃~8℃ 晴转多云 0-3级 <5.4m/s
         建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
         * relquestion : {"0":"查询天气[那么?][天气地区名|省名|城市别称][天气地区名?]"}
         */

        private String type;
        private String content;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }



    }
}

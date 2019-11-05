package swust.yuqiaodan.tomatoapp.mvp.model.api;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * <p>
 * Created by MVPArmsTemplate on 08/20/2019 15:58
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Api {
    //这个接口基本上都可以用GET来直接请求，那个接口文档说明有点坑
    String APP_DOMAIN = "https://api.apiopen.top/";//开源api Baseurl1
    String BASEURL2="https://www.apiopen.top/";//有两个api接口
    String WEATHER_API="https://www.tianqiapi.com/api";
}

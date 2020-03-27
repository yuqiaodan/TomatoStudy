package swust.yuqiaodan.tomatoapp.mvp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import swust.yuqiaodan.tomatoapp.R;

import static swust.yuqiaodan.tomatoapp.app.Constants.HTMLCONTENT;
import static swust.yuqiaodan.tomatoapp.app.Constants.HTMLTITLE;

public class ShowHtmlActivity extends AppCompatActivity {
    HtmlTextView htmlTextView;
    TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_html);
        htmlTextView=findViewById(R.id.html_view);
        View titleBar=findViewById(R.id.html_title_bar);
        titleView=titleBar.findViewById(R.id.toolbar_title);
        dealIntent();

    }
    public void dealIntent(){
        Intent intent=getIntent();
        String html=intent.getStringExtra(HTMLCONTENT);
        String title=intent.getStringExtra(HTMLTITLE);
        htmlTextView.setHtml(html, new HtmlHttpImageGetter(htmlTextView));
        titleView.setText(title);
    }

}

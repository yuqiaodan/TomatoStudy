package swust.yuqiaodan.tomatoapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DST_OFFSET;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.ZONE_OFFSET;

public class TimeTest {
    public static void main(String[] args) {
        System.out.print(getUTCTime());
    }

    public static String getUTCTime() {
        Calendar calendar= Calendar.getInstance();
        calendar.add(MILLISECOND, -(calendar.get(ZONE_OFFSET) + calendar.get(DST_OFFSET)));

        String filename=String.format("%02d",calendar.get(Calendar.MONTH)+1)+
                        String.format("%02d",calendar.get(Calendar.DATE))+
                        String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY))+
                        String.format("%02d",calendar.get(Calendar.MINUTE))+
                        String.format("%02d",calendar.get(Calendar.SECOND));

        System.out.println(String.format("%02d",calendar.get(Calendar.MONTH)+1));
        System.out.println(String.format("%02d",calendar.get(Calendar.DATE)));
        System.out.println(String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY)));
        System.out.println(String.format("%02d",calendar.get(Calendar.MINUTE)));
        System.out.println(String.format("%02d",calendar.get(Calendar.SECOND)));


        return filename;
    }
}

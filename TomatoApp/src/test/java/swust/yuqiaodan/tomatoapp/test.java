package swust.yuqiaodan.tomatoapp;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class test {


    public static void main(String[] args) {

        String str="DE123456";
        if (isNumeric(str)){
            System.out.print("true");
        }
        else {

            System.out.print("false");
        }


    }

    public static boolean isNumeric(String str){

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(str);
        if(!matcher.matches()){

            return false;
        }

        return true;
    }



}

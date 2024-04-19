package leetcode;

import java.util.regex.Pattern;

public class CommUtils {


    public boolean checkIsNumber(String str){
        String regex = "[0-9]+[\\.]?[0-9]*";
        return Pattern.matches(regex, str);
    }
}

package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Zstr {

    public String convert(String s, int numRows) {
        int r = numRows;
        int n = s.length();

        // 特殊条件处理
        if(s.length() <= 2){
            return s;
        }
        // 把每行表示出来
        List<StringBuilder> mat = new ArrayList<>();
        for (int i = 0; i < numRows ; i++) {
            mat.add(new StringBuilder());
        }
        //给每一行赋值
        for (int i = 0, x=0, t=r*2-2; i <n; i++) {
            mat.get(x).append(s.charAt(i));
            if (i%t<r-1) {
                ++x;
            }else {
                --x;
            }
        }

        //将strb每行都放入字符
        StringBuffer ans = new StringBuffer();
         for (StringBuilder str : mat) {
             ans.append(str);
        }
        return ans.toString();

    }

}

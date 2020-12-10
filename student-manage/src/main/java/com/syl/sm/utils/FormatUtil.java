package com.syl.sm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName FormatUtil
 * @Description TODO
 * @Author admin
 * @Date 2020/12/10
 **/
public class FormatUtil {
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String formatGender(short gender) {
        if (gender == 0) {
            return "保密";
        } else if (gender == 1) {
            return "男";
        } else {
            return "女";
        }
    }

    public static Short formatGender(String gender) {
        if ("保密".equals(gender)) {
            return 0;
        } else if ("男".equals(gender)) {
            return 1;
        } else {
            return 2;
        }
    }
}

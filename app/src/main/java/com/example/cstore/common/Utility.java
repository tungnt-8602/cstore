package com.example.cstore.common;

import java.text.DecimalFormat;

public class Utility {
    //Công ty: máy thật
//    public static String BASE_URL = "http://192.168.95.222:9999/";
    //Công ty: máy ảo
    public static String BASE_URL = "http://192.168.0.101:9999/";

    //Nhà
//    public static String BASE_URL = "http://192.168.0.102:9999/";

//    public static String BASE_URL = "http://192.168.60.222:9999/";


    public static String formatIntNumber(Integer number){
        return new DecimalFormat("#,###").format(number);
    }

    public static String formatDoubleNumber(Double number){
        return new DecimalFormat("#,###.00").format(number);
    }
}

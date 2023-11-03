package com.example.cstore.common;

import android.content.Context;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.Objects;

public class Utility {
    //Công ty: máy thật
//    public static String BASE_URL = "http://192.168.95.222:9999/";
    //Công ty: máy ảo
    public static String BASE_URL = "http://192.168.27.110:9999/";

    //Nhà
//    public static String BASE_URL = "http://192.168.0.101:9999/";

    public static void showMenuPopup(TextView v, int menuRes, Context context){
        PopupMenu popup = new PopupMenu(context, v);
        popup.getMenuInflater().inflate(menuRes, popup.getMenu());
        popup.setOnMenuItemClickListener(menuItem -> {
            v.setText(Objects.requireNonNull(menuItem.getTitle()).toString());
            return true;
        });
        popup.show();
    }
}

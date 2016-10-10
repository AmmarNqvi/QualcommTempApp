package com.example.android.Qualcomm_Temp;

import java.util.ArrayList;

/**
 * Days List.
 */
public class Days {
    static final String[] Days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    public static ArrayList<String> asList() {
        ArrayList<String> items = new ArrayList<String>();
        for (int i = 0; i<Days.length ; i++) {
            items.add(Days[i]);
        }
        return items;
    }



}

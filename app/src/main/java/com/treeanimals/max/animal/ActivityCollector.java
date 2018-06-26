package com.treeanimals.max.animal;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by 95112 on 2018/6/25.
 */

public class ActivityCollector {
    public static ArrayList<Activity> activities= new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for ( Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}

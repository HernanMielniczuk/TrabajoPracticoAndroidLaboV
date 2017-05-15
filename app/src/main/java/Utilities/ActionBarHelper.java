package Utilities;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Hernan on 06/05/2017.
 */

public class ActionBarHelper {

    private ActionBarHelper(){}

    public static void invalidateActionBar(AppCompatActivity activity){
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar!= null) {
            actionBar.hide();
        }
    }
}

package me.doapps.curiosidad.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by jonathan on 23/11/2014.
 */
public class UtilFonts {

    public static Typeface setHelveticaBold(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/Helvetica_Bold.otf");
    }
    public static Typeface setHelveticaMedium(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/Helvetica_Medium.otf");
    }
    public static Typeface setHelveticaRoman(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/Helvetica_Roman.otf");
    }
    public static Typeface setHelveticaThin(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/Helvetica_Thin.otf");
    }
}

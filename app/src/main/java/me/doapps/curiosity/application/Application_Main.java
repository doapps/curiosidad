package me.doapps.curiosity.application;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import me.doapps.curiosity.beans.Curiosidad_DTO;
import me.doapps.curiosity.R;

public class Application_Main extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Curiosidad_DTO.class);
        Parse.initialize(this, getString(R.string.application_id), getString(R.string.client_key));
    }
}

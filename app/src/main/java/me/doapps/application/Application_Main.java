package me.doapps.application;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import me.doapps.beans.Curiosidad_DTO;
import me.doapps.curiosidades.R;

public class Application_Main extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Curiosidad_DTO.class);
        Parse.initialize(this, getString(R.string.parse_app_ip), getString(R.string.parse_client_key));
    }
}

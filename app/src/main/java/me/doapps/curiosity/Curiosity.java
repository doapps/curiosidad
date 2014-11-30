package me.doapps.curiosity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import me.doapps.beans.Curiosidad_DTO;
import me.doapps.fragments.Fragment_Splash;


public class Curiosity extends ActionBarActivity {

    private Curiosidad_DTO curiosidad_DTO;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curiosity);

        /**
         * Load Intersticial
         */
        loadIntersticial();


        /**
         * Load Splash
         */
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, Fragment_Splash.newInstance())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.curiosity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Curiosidad_DTO getCuriosidad_DTO() {
        return curiosidad_DTO;
    }

    public void setCuriosidad_DTO(Curiosidad_DTO curiosidad_DTO) {
        this.curiosidad_DTO = curiosidad_DTO;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            if (getInterstitial().isLoaded()) {
                getInterstitial().show();
            }
            super.onBackPressed();
        }
    }

    private void loadIntersticial() {
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(getResources().getString(R.string.admob_interstitial));
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitial.loadAd(adRequest);
    }

    public InterstitialAd getInterstitial() {
        return interstitial;
    }
}

package me.doapps.curiosity.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.doapps.curiosity.R;
import me.doapps.curiosity.adapter.Adapter_Lista;
import me.doapps.curiosity.beans.Curiosidad_DTO;
import me.doapps.curiosity.dialogs.Dialog_Curiosity;
import me.doapps.curiosity.dialogs.Dialog_Detail;
import me.doapps.curiosity.utils.InternetUtil;

/**
 * Created by mili on 30/12/2014.
 */
public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private Curiosidad_DTO curiosidad_DTO;
    private ListView listaCuriosidad;
    private Dialog_Curiosity dialog;

    private InterstitialAd interstitial;

    private int TIME_INTERVAL = 7000;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list);

        loadBanner();
        loadIntersticial();

        /*timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       loadIntersticial();
                       getInterstitial().show();
                       Toast.makeText(MainActivity.this, "toast", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, 0, TIME_INTERVAL);*/

        /**Internet Status**/
        InternetUtil internet = new InternetUtil(MainActivity.this);

        if (!internet.isConnectingToInternet()) {
            Dialog_Curiosity dialog = new Dialog_Curiosity(MainActivity.this);
            dialog.show();
        } else {
            /**
             * Load List
             */
            listaCuriosidad = (ListView) findViewById(R.id.lista_curiosidades);
            listaCuriosidad.setOnItemClickListener(this);

            dialog =  new Dialog_Curiosity(MainActivity.this);


            ParseQuery<Curiosidad_DTO> query = Curiosidad_DTO.getQuery();
            query.findInBackground(new FindCallback<Curiosidad_DTO>() {

                @Override
                public void done(List<Curiosidad_DTO> objects, ParseException e) {
                    if (e == null) {
                        (findViewById(R.id.loading_curiosity)).setVisibility(View.GONE);
                        listaCuriosidad.setVisibility(View.VISIBLE);
                        listaCuriosidad.setAdapter(new Adapter_Lista(MainActivity.this, objects));
                    } else {
                        e.printStackTrace();
                        dialog.show();
                    }
                }
            });

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Curiosidad_DTO curiosidad_DTO = (Curiosidad_DTO) parent.getItemAtPosition(position);

        /**
         * Show Detail Curiosity
         */
        Dialog_Detail dialog_detail = new Dialog_Detail(MainActivity.this, curiosidad_DTO);
        dialog_detail.getWindow().setWindowAnimations(R.style.Dialog_Animation_UP_DOWN);
        dialog_detail.show();
    }

    @Override
    public void onBackPressed() {
            if (getInterstitial().isLoaded()) {
                getInterstitial().show();
            }
            super.onBackPressed();
    }

    private void loadIntersticial() {
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(getResources().getString(R.string.admob_interstitial));
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitial.loadAd(adRequest);
    }

    private void loadBanner(){
        AdView adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequestb= new AdRequest.Builder().build();
        adView.loadAd(adRequestb);
    }

    public InterstitialAd getInterstitial() {
        return interstitial;
    }


}

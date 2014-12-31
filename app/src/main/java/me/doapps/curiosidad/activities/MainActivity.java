package me.doapps.curiosidad.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import me.doapps.curiosidad.R;
import me.doapps.curiosidad.adapter.Adapter_Lista;
import me.doapps.curiosidad.adapter.CuriosityAdapter;
import me.doapps.curiosidad.beans.Curiosidad_DTO;
import me.doapps.curiosidad.beans.Curiosity_DTO;
import me.doapps.curiosidad.dialogs.Dialog_Curiosity;
import me.doapps.curiosidad.dialogs.Dialog_Detail;
import me.doapps.curiosidad.utils.InternetUtil;

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

    private AutoCompleteTextView edt_search;
    private Curiosity_DTO curiosity_dto;
    private ArrayList<Curiosity_DTO> curiosity_dtos;
    private List<Curiosidad_DTO> curiosidad_dtos = null;

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

            edt_search = (AutoCompleteTextView)findViewById(R.id.edt_search);
            edt_search.setThreshold(0);


            ParseQuery<Curiosidad_DTO> query = Curiosidad_DTO.getQuery();
            query.findInBackground(new FindCallback<Curiosidad_DTO>() {

                @Override
                public void done(List<Curiosidad_DTO> objects, ParseException e) {
                    if (e == null) {
                        curiosidad_dtos = objects;

                        (findViewById(R.id.loading_curiosity)).setVisibility(View.GONE);
                        listaCuriosidad.setVisibility(View.VISIBLE);
                        listaCuriosidad.setAdapter(new Adapter_Lista(MainActivity.this, objects));

                        curiosity_dtos = new ArrayList<Curiosity_DTO>();
                        for (int i = 0; i < objects.size(); i++) {
                            curiosity_dtos.add(new Curiosity_DTO(objects.get(i).getObjectId(),objects.get(i).getNameCuriosidad(),objects.get(i).getDescriptionCuriosidad(),objects.get(i).getImage_url()));
                        }
                        edt_search.setAdapter(new CuriosityAdapter(curiosity_dtos, MainActivity.this));
                    } else {
                        e.printStackTrace();
                        dialog.show();
                    }
                }
            });


            edt_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    curiosity_dto = (Curiosity_DTO)parent.getItemAtPosition(position);
                    edt_search.setText(curiosity_dto.getNameCuriosidad());

                    List<Curiosidad_DTO> temp_curiosidad_dtos = null;
                    for (int i = 0; i < curiosidad_dtos.size(); i++) {
                        if(curiosidad_dtos.get(i).getNameCuriosidad().toUpperCase().equals(curiosity_dto.getNameCuriosidad().toUpperCase())){
                            temp_curiosidad_dtos.add(curiosidad_dtos.get(i));
                        }
                    }
                    listaCuriosidad.setAdapter(new Adapter_Lista(MainActivity.this, temp_curiosidad_dtos));
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

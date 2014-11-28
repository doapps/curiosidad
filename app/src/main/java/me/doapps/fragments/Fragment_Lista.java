package me.doapps.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import me.doapps.adapter.Adapter_Lista;
import me.doapps.beans.Curiosidad_DTO;
import me.doapps.curiosity.Curiosity;
import me.doapps.curiosity.R;
import me.doapps.dialogs.Dialog_Detail;

public class Fragment_Lista extends Fragment implements OnItemClickListener {

    private Curiosity curiosity;
    private ListView listaCuriosidad;

    public static final Fragment_Lista newInstance() {
        return new Fragment_Lista();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        curiosity = ((Curiosity) getActivity());
        ((Curiosity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View localView = inflater.inflate(R.layout.fragment_list, container, false);
        localView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        return localView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /**
         * Load Intersticial
         */
        if (curiosity.getInterstitial().isLoaded()) {
            curiosity.getInterstitial().show();
        }

        /**
         * Load Banner
         */
        loadBanner();

        /**
         * Load List
         */
        listaCuriosidad = (ListView) getView().findViewById(R.id.lista_curiosidades);
        listaCuriosidad.setOnItemClickListener(this);

        ParseQuery<Curiosidad_DTO> query = Curiosidad_DTO.getQuery();
        query.findInBackground(new FindCallback<Curiosidad_DTO>() {

            @Override
            public void done(List<Curiosidad_DTO> objects, ParseException e) {
                if (e == null) {
                    listaCuriosidad.setAdapter(new Adapter_Lista(getActivity(), objects));
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Curiosidad_DTO curiosidad_DTO = (Curiosidad_DTO) parent.getItemAtPosition(position);
        curiosity.setCuriosidad_DTO(curiosidad_DTO);

        /**
         * Show Detail Curiosity
         */
        Dialog_Detail dialog_detail = new Dialog_Detail(getActivity(), (Curiosity) getActivity());
        dialog_detail.getWindow().setWindowAnimations(R.style.Dialog_Animation_UP_DOWN);
        dialog_detail.show();
    }

    private void loadBanner() {
        AdView adView = (AdView) getView().findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
}

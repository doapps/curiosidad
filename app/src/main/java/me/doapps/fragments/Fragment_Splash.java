package me.doapps.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import me.doapps.curiosity.Curiosity;
import me.doapps.curiosity.R;

public class Fragment_Splash extends Fragment {

    private Curiosity curiosity;
    private static int SPLASH_TIME_OUT = 3000;

    public static final Fragment_Splash newInstance() {
        return new Fragment_Splash();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        curiosity = ((Curiosity) getActivity());
        ((Curiosity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View localView = inflater.inflate(R.layout.fragment_splash, container, false);
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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (curiosity.getInterstitial().isLoaded()) {
                    curiosity.getInterstitial().show();
                }
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, Fragment_Lista.newInstance())
                        .commit();
            }
        }, SPLASH_TIME_OUT);
    }
}
